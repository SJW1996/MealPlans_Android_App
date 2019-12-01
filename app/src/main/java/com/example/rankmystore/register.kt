package com.example.rankmystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast
import android.view.View.OnClickListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*
//import jdk.nashorn.internal.runtime.ECMAException.getException
import com.google.firebase.auth.FirebaseAuthException
import android.util.Log
import com.google.firebase.FirebaseNetworkException

class register : AppCompatActivity(){
    //private lateinit var mAuth: FirebaseAuth
   // val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: FirebaseDatabase
    private var mAuth: FirebaseAuth? = null

    // lateinit var mDatabase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        mAuth = FirebaseAuth.getInstance()

        mDatabase = FirebaseDatabase.getInstance()
        var register_button = findViewById<View>(R.id.register_btn) as Button
        register_button.setOnClickListener({register_new_user()})
        //Go back to the login page
        var login_button = findViewById<View>(R.id.tvlogin) as Button
        login_button.setOnClickListener({goto_Loginactivity()})



    }

    private fun register_new_user(){

        val emailText = findViewById<View>(R.id.etusername) as EditText
        val passWordText = findViewById<View>(R.id.etpassword) as EditText

        var email = emailText.text.toString()
        var password = passWordText.text.toString()
        if (!email.isEmpty() && !password.isEmpty()){
            mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    val user = mAuth!!.currentUser
                    val uid = user!!.uid
                    //adding review
                    //mDatabase.child(uid).child("Name").setValue(name)
                    startActivity(Intent(this,login::class.java))
                    Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show()

                }else{
                    val e = task.exception as FirebaseNetworkException
                    Log.e("Register activity", "Failed Registration", e)
                    Toast.makeText(this, "Error registering, please try again", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this, "Please fill up correct information", Toast.LENGTH_LONG).show()
        }
    }
    fun goto_Loginactivity(){
        val intent = Intent(this, login::class.java)
        startActivity(intent)
    }
}