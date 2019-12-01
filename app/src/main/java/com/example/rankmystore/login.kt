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
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class login : AppCompatActivity(){
    //private lateinit var mAuth: FirebaseAuth
    lateinit var mDatabase: FirebaseDatabase
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        var input_email = findViewById<View>(R.id.input_email) as EditText
        var input_password = findViewById<View>(R.id.input_password) as EditText
       // val email = input_email.text.toString()
       // var password = input_password.text.toString()


        var btn_reset = findViewById<View>(R.id.btn_reset) as Button
        var btn_submit = findViewById<View>(R.id.btn_submit) as Button

        var sign_up_button = findViewById<View>(R.id.btn_sign_up) as Button


        btn_reset.setOnClickListener{
            input_email.setText("")
            input_password.setText("")
        }

        btn_submit.setOnClickListener{
            login_user()
            //Toast.makeText(this@login,  input_email,Toast.LENGTH_LONG).show()
        }

        sign_up_button.setOnClickListener({gotoregisterActivity()})
    }

    private fun login_user(){
        var input_email = findViewById<View>(R.id.input_email) as EditText
        var input_password = findViewById<View>(R.id.input_password) as EditText
        val email = input_email.text.toString()
        var password = input_password.text.toString()

        if (!email.isEmpty() && !password.isEmpty()){
            this.mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener<AuthResult>{
                    task ->
                if (task.isSuccessful){
                    //go to which activity
                    startActivity(Intent(this, Profile::class.java))
                    Toast.makeText(this, "Successfully Login in to account", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error Login in", Toast.LENGTH_SHORT).show()
                }
            })
        }else{
            Toast.makeText(this, "Please fill up the Login Information", Toast.LENGTH_SHORT).show()
        }
    }
    fun gotoregisterActivity(){
        val register_intent = Intent(this, register::class.java)
        startActivity(register_intent)
    }
}