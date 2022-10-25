package com.thebobighat.emp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar

class FragmentHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation_view).menu.findItem(R.id.nav_home).isChecked = true

        Toast.makeText(activity, "Loading latest array, please wait...", Toast.LENGTH_LONG).show()

        val api = RetrofitInstance.getInstance().create(TdgApi::class.java)
        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("TDG_APP_ODN", Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putInt("active_fragment", 0)?.apply()

        view.findViewById<Button>(R.id.mark_attendance_btn)?.setOnClickListener {
            val pwd = view.findViewById<EditText>(R.id.editTextPassword).text.toString()

            if (pwd != sharedPreferences?.getString("password", "NULL")) {
                Toast.makeText(activity, "Invalid password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launchWhenCreated {
                val response = try {
                    api.postAttendance("Bearer ${sharedPreferences.getString("jwt", "NULL")}")
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

                if (response.isSuccessful) {
                    val date = Calendar.getInstance().time
                    val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
                    val formattedDate = formatter.format(date)

                    Toast.makeText(activity, "Attendance registered for $formattedDate", Toast.LENGTH_SHORT).show()

                }
            }
        }

        return view
    }
}