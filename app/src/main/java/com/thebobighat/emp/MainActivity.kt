package com.thebobighat.emp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "The Dhobi Ghat"

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> setCurrentFragment(0, FragmentHome())
                R.id.nav_new_tasks -> setCurrentFragment(1, FragmentNewTasks())
                R.id.nav_my_tasks -> setCurrentFragment(2, FragmentMyTasks())
                R.id.nav_service_history -> setCurrentFragment(3, FragmentServiceHistory())
                else -> { setCurrentFragment(0, FragmentHome()) }
            }
            true
        }

        bottomNavigationView.setOnItemReselectedListener { }
    }

    private fun setCurrentFragment(fragmentIndex: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            // need to set active fragment in every fragment
            if (fragmentIndex - getSharedPreferences("TDG_APP_ODN", MODE_PRIVATE).getInt("active_fragment", 0) > 0)
                setCustomAnimations(
                    R.anim.slide_in_from_right,
                    R.anim.slide_out_to_left,
                    R.anim.slide_in_from_left,
                    R.anim.slide_out_to_right
                )
            else
                setCustomAnimations(
                    R.anim.slide_in_from_left,
                    R.anim.slide_out_to_right,
                    R.anim.slide_out_to_left,
                    R.anim.slide_in_from_right,
                )

            val bundle = Bundle()
            bundle.putInt("index", fragmentIndex)
            fragment.arguments = bundle
            replace(R.id.main_fragment, fragment)
            commit()
        }
    }
}