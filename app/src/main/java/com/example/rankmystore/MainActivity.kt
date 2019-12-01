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

        val storeButton = findViewById<View>(R.id.store_button)
        storeButton.setOnClickListener({goToStoreActivity()})

    }


    fun goToAnActivity( ) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    fun goToLoginActivity( ) {
        val login_intent = Intent(this, login::class.java)
        startActivity(login_intent)
    }

    fun goToStoreActivity( ) {
        val store_intent = Intent(this, Stores::class.java)
        startActivity(store_intent)
    }


}


