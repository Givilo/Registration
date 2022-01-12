package com.example.registrationactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var registerButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()
        checkListeners()
        checkPasswords()

    }

    private fun init(){
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        registerButton = findViewById(R.id.registerButton)

    }
    private fun checkListeners(){
        registerButton.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()

            if (email.isEmpty()|| password.isEmpty()|| confirmPassword.isEmpty()){
                Toast.makeText(this,"Empty!",Toast.LENGTH_LONG).show()

            }

        }

    }
    private fun checkPasswords(){
        registerButton.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()


            if(!confirmPassword.equals(password)){
                Toast.makeText(this,   "Passwords do not match!",Toast.LENGTH_LONG).show()
            }else{
                FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task->
                        if (task.isSuccessful){
                            Toast.makeText(this,"You have successfully registered",Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this,"Error!",Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }



}

