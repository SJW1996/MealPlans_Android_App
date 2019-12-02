package com.example.rankmystore

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.service.autofill.Dataset
import android.view.Gravity
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import io.opencensus.metrics.export.Summary


class Profile : AppCompatActivity() {

    lateinit var mDatabase : DatabaseReference
    var user = FirebaseAuth.getInstance().currentUser


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val horizontalScrollView = HorizontalScrollView(this)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)


        horizontalScrollView.layoutParams = layoutParams

        val linearLayout = LinearLayout(this)
        val linearParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.layoutParams = linearParams

        horizontalScrollView.addView(linearLayout)

        val imageView1 = ImageView(this)
        val params1 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params1.setMargins(30, 20, 30, 0)
        params1.gravity = Gravity.CENTER
        imageView1.layoutParams = params1
        imageView1.setImageResource(R.drawable.test1)
        linearLayout.addView(imageView1)

        val imageView2 = ImageView(this)
        val params2 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.setMargins(0, 20, 30, 0)
        params2.gravity = Gravity.CENTER
        imageView2.layoutParams = params2
        imageView2.setImageResource(R.drawable.test2)
        linearLayout.addView(imageView2)

        val imageView3 = ImageView(this)
        val params3 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params3.setMargins(0, 20, 30, 0)
        params3.gravity = Gravity.CENTER
        imageView3.layoutParams = params3
        imageView3.setImageResource(R.drawable.test3)
        linearLayout.addView(imageView3)

        val linearLayout1 = findViewById<LinearLayout>(R.id.horizontalScroll)

        linearLayout1?.addView(horizontalScrollView)

        mDatabase = FirebaseDatabase.getInstance().getReference()
        val name_text = findViewById<View>(R.id.profile_name) as TextView
        var uid = user!!.uid
        mDatabase.child(uid).child("username").addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                name_text.text =  "Welcome " + snapshot.value.toString()
            }
        })

        var edit_profile = findViewById<View>(R.id.button_editprofile) as Button
        edit_profile.setOnClickListener{
            val intent = Intent(this, Edit_Profile::class.java)
            startActivity(intent)
        }

    }


}

