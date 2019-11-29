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

class login : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var input_user_name = findViewById<View>(R.id.input_user_name) as EditText
        var input_password = findViewById<View>(R.id.input_password) as EditText
        var btn_reset = findViewById<View>(R.id.btn_reset) as Button

        var btn_submit = findViewById<View>(R.id.btn_submit) as Button

        btn_reset.setOnClickListener{
            input_user_name.setText("")
            input_password.setText("")
        }
        btn_submit.setOnClickListener{
            val user_name = input_user_name.text
            val password = input_password.text
            Toast.makeText(this@login,  user_name,Toast.LENGTH_LONG).show()
        }
    }
}