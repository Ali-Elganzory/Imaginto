package com.ganzory.imaginto_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_menu.*

/****   Made by Ali Elganzory 29/7/2019    ****/

class Menu : AppCompatActivity() {

    var tempUserAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        login_btn.setOnClickListener {

            if (log_email_edit.text.isNotEmpty() && log_pass_edit.text.isNotEmpty()){

                loadingProgressBar.visibility = View.VISIBLE

                tempUserAuth = FirebaseAuth.getInstance()
                tempUserAuth?.signInWithEmailAndPassword(log_email_edit.text.toString(), log_pass_edit.text.toString())
                    ?.addOnCompleteListener {
                        if (it.isSuccessful){

                            loadingProgressBar.visibility = View.GONE

                            activeUser = tempUserAuth?.currentUser
                            startActivity(Intent(this, Insides::class.java))
                        } else {
                            loadingProgressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                warn_menu.visibility = View.VISIBLE
            }

        }

        register_btn.setOnClickListener{
            startActivity(Intent(this, UserRegistration::class.java))
        }

    }

}

var activeUser: FirebaseUser? = null

