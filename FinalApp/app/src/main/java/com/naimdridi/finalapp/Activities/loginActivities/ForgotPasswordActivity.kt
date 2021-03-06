package com.naimdridi.finalapp.Activities.loginActivities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.naimdridi.finalapp.R
import com.naimdridi.my_library_second.Interfaces.Others.goToActivity
import com.naimdridi.my_library_second.Interfaces.Others.isValidEmail
import com.naimdridi.my_library_second.Interfaces.Others.toast
import com.naimdridi.my_library_second.Interfaces.Others.validate
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        validateEmail()
        clickListener()

    }


    private fun validateEmail(){

        editTextEmailReset.validate {

            editTextEmailReset.error = if (isValidEmail(it)) null else "Email is not valid"
        }
    }

    private fun clickListener(){

        buttonForgot.setOnClickListener {
            val email = editTextEmailReset.text.toString()
            if (isValidEmail(email)){
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this) {
                    toast("Email has been sent to reset your password")
                    goToActivity<LoginActivity>{ flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }else{
                toast("Please make sure the email address is correct")
            }
        }




        buttonGoLogIn.setOnClickListener {
            goToActivity<LoginActivity>{ flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out) }
    }
}
