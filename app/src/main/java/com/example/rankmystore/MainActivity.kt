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
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileBoutton = findViewById<View>(R.id.button3)
        profileBoutton.setOnClickListener { goToAnActivity() }
        val login_button = findViewById<View>(R.id.button2)
        login_button.setOnClickListener({goToLoginActivity()})
        //ss
        /*
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
            Toast.makeText(this@MainActivity,  user_name,Toast.LENGTH_LONG).show()
        }

         */
    }


    fun goToAnActivity( ) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    fun goToLoginActivity( ) {
        val login_intent = Intent(this, login::class.java)
        startActivity(login_intent)
    }



}


