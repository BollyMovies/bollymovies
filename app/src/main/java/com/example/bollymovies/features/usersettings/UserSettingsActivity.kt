package com.example.bollymovies.features.usersettings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bollymovies.R
import com.example.bollymovies.databinding.ActivityUserSettingsBinding
import com.example.bollymovies.features.SplashActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserSettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogOut.setOnClickListener {
            logout()
        }

        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun logout() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(this, gso).signOut()
        Firebase.auth.signOut()

        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }
}