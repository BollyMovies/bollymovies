package com.example.bollymovies.features.usersettings.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bollymovies.R
import com.example.bollymovies.databinding.ActivityUserSettingsBinding
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class UserSettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    val gso = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()


}
