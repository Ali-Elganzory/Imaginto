package com.ganzory.imaginto_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_user_registration.*

/****   Made by Ali Elganzory 29/7/2019    ****/

class UserRegistration : AppCompatActivity() {

    var userAuth: FirebaseAuth? = null
    private var userDataRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        userAuth = FirebaseAuth.getInstance()
        userDataRef = FirebaseDatabase.getInstance().reference
    }

    override fun onStart() {
        super.onStart()

        reg_btn.setOnClickListener {

            if (fName_edit.text.isNotEmpty() && lName_edit.text.isNotEmpty() && phoneN_edit.text.isNotEmpty()
                && email_edit.text.isNotEmpty() && password_edit.text.isNotEmpty()){

                progressBar.visibility = View.VISIBLE

                userAuth?.createUserWithEmailAndPassword(email_edit.text.toString(), password_edit.text.toString())
                    ?.addOnCompleteListener{

                        if (it.isSuccessful){
                            var currentUser = userAuth!!.currentUser
                            var profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(fName_edit.text.toString() + " " + lName_edit.text.toString())
                                .build()

                            currentUser?.updateProfile(profileUpdates)
                            userDataRef?.child("Users")?.child(currentUser!!.uid)?.child("Profile")?.setValue(UserProfile(
                                  fName_edit.text.toString()
                                , lName_edit.text.toString()
                                , phoneN_edit.text.toString()))

                            progressBar.visibility = View.GONE

                            fields_container.visibility = View.GONE
                            warn_textview.visibility = View.GONE
                            reg_btn.visibility = View.GONE

                            completedReg.visibility = View.VISIBLE
                            goBack_btn.visibility = View.VISIBLE
                        }
                        else {
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            } else warn_textview.visibility = View.VISIBLE
        }

        goBack_btn.setOnClickListener {
            finish()
        }

    }

}
