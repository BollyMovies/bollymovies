package com.example.bollymovies.features.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout.make
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.bollymovies.databinding.ActivityRegisterBinding
import com.example.bollymovies.utils.ConstantsApp
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrowRegister.setOnClickListener {
            onBackPressed()
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.fieldEmail.text.toString()
            val password: String = passwordVerification()
            if (email.isNotEmpty() && password.isNotEmpty() && password.length >= 6) {
                registerInFirebase(email, password)
                Toast.makeText(
                    this,
                    "E-mail de verificação enviado",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else if (email.isNotEmpty() && password.isNotEmpty() && password.length < 6) {
                Toast.makeText(
                    this,
                    "A senha deve conter no mínimo 6 caracteres",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Preencha todos os campos corretamente",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun passwordVerification(): String {
        if (binding.fieldPassword.text.toString() == binding.fieldPasswordConfirmation.text.toString()) {
            return binding.fieldPassword.text.toString()
        } else {
            return ""
        }
    }

    private fun registerInFirebase(mail: String, pass: String) {
        auth.createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                updateUI(user)
                            }
                        }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(LoginActivity.TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }
}