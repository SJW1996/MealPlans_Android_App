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

class Review  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        var add_Photo_Button = findViewById<View>(R.id.addStorePhotosButton)
        var done_Button = findViewById<View>(R.id.submitButton)
        var storeName = findViewById<View>(R.id.storeName)
        var address = findViewById<View>(R.id.Address)
        var comments = findViewById<View>(R.id.Comments)

        done_Button.setOnClickListener(){finishAddPhotos()}




    }

    fun finishAddPhotos(){
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}