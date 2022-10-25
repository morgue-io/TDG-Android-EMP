package com.thebobighat.emp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.HttpException
import java.io.IOException

class FragmentServiceHistory : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_service_history, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "Employee Service History"

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view).menu.findItem(R.id.nav_service_history).isChecked = true

        val api = RetrofitInstance.getInstance().create(TdgApi::class.java)
        val sharedPreferences: SharedPreferences? =
            activity?.getSharedPreferences("TDG_APP_ODN", Context.MODE_PRIVATE)

        sharedPreferences?.edit()?.putInt("active_fragment", 3)?.apply()

        lifecycleScope.launchWhenCreated {
            val response = try {
                api.getServiceHistory("Bearer ${sharedPreferences?.getString("jwt", "NULL")}")
            } catch (e: IOException) {
                Toast.makeText(
                    activity,
                    "IOException, you might not have internet connection",
                    Toast.LENGTH_SHORT
                ).show()
                return@launchWhenCreated
            } catch (e: HttpException) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }
            Log.i("hi", "${response.body().toString()} ${response.code()}")

            if (response.body() != null) {
                val adapter = AdapterServiceHistory(response.body()!!.payload, parentFragmentManager)
                val rv = view.findViewById<RecyclerView>(R.id.recycler_view)
                rv.layoutManager = LinearLayoutManager(activity)
                rv.adapter = adapter
            }
        }

        return view
    }
}