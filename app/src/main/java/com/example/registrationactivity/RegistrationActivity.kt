package com.example.registrationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var buttonRegistration: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        findView()
        listeners()
        passwords()

    }

    private fun findView(){
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        buttonRegistration = findViewById(R.id.buttonRegistration)

    }
    private fun listeners(){
        buttonRegistration.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            val confirmPassword = confirmPassword.text.toString()

            if (email.isEmpty()|| password.isEmpty()|| confirmPassword.isEmpty()){
                Toast.makeText(this,"Error!",Toast.LENGTH_LONG).show()

            }

        }

    }
    private fun passwords(){
        buttonRegistration.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            val confirmPassword = confirmPassword.text.toString()


            if(!confirmPassword.equals(password)){
                Toast.makeText(this,   "პაროლები არ ემთხვევა",Toast.LENGTH_LONG).show()
            }else{
                FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task->
                        if (task.isSuccessful){
                            Toast.makeText(this,"თქვენ წარმატებით დარეგისტრირდით",Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this,"Error!",Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }



}

