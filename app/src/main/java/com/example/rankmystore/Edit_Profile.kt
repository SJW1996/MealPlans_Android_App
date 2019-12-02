package com.example.rankmystore


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.wifi.rtt.WifiRttManager
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
import java.util.ArrayList
class Edit_Profile  : AppCompatActivity() {

    lateinit var mDatabase : DatabaseReference
    private var mAuth: FirebaseAuth? = null
    private var user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        var update_button = findViewById<View>(R.id.update_btn)
        update_button.setOnClickListener{
            update_user_info()
        }

    }

    private fun update_user_info(){
    	//val emailText = findViewById<View>(R.id.etusername) as EditText
        //val passWordText = findViewById<View>(R.id.etpassword) as EditText
        val user_nameText = findViewById<View>(R.id.et_other_username) as EditText


        //var new_email = emailText.text.toString()
        //var new_password = passWordText.text.toString()
        var new_username = user_nameText.text.toString()

        var uid = user!!.uid

        if (!new_username.isEmpty()){

        	//mDatabase.child(uid).child("username")

            //mDatabase.child(uid).child("email").setValue(new_email)
            //user!!.updatePassword(new_password)
            //mDatabase.child(uid).child("password").setValue(new_password)
            mDatabase.child(uid).child("username").setValue(new_username)
            Toast.makeText(this, "Update Successfully", Toast.LENGTH_LONG).show()


        }
    }
}