package com.thebobighat.emp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import retrofit2.HttpException
import java.io.IOException


class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val api = RetrofitInstance.getInstance().create(TdgApi::class.java)

        findViewById<Button>(R.id.button).setOnClickListener { _ ->
            val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            lifecycleScope.launchWhenCreated {
                val response = try {
                    api.postCredentials(LoginCredentials(email, password))
                } catch(e: IOException) {
                    Toast.makeText(this@LoginActivity, "IOException, you might not have internet connection", Toast.LENGTH_SHORT).show()
                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
                    return@launchWhenCreated
                }
                if (response.body() != null) {
                    Log.i("TDG", response.body().toString())
                    this@LoginActivity.getSharedPreferences("TDG_APP_ODN", MODE_PRIVATE).edit().putString("jwt", response.body()!!.jwt).apply()
                    this@LoginActivity.getSharedPreferences("TDG_APP_ODN", MODE_PRIVATE).edit().putString("jwt_refresh", response.body()!!.jwt_refresh).apply()
                    this@LoginActivity.getSharedPreferences("TDG_APP_ODN", MODE_PRIVATE).edit().putString("email", email).apply()
                    this@LoginActivity.getSharedPreferences("TDG_APP_ODN", MODE_PRIVATE).edit().putString("password", password).apply()
                    val activityMain = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(activityMain)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Request rejected: invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}