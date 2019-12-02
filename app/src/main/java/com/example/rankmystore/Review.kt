package com.example.rankmystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Review  : AppCompatActivity(){

    private var mAuth: FirebaseAuth? = null
    lateinit var mDatabase : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference

        var add_Photo_Button = findViewById<View>(R.id.addStorePhotosButton)
        var done_Button = findViewById<View>(R.id.submitButton)


        // set up rating bar
        val tv = findViewById<TextView>(R.id.textView7)
        findViewById<RatingBar>(R.id.ratingBar)?.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                // Called when the user swipes the RatingBar
                tv.text = getString(R.string.rating_string, rating)
            }

        done_Button.setOnClickListener(){finishAddPhotos()}
        add_Photo_Button.setOnClickListener(){goToGallery()}


    }

    fun finishAddPhotos(){
        var storeNameView = findViewById<View>(R.id.storeName) as EditText
        var addressView = findViewById<View>(R.id.Address) as EditText
        var ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar

        var storeName = storeNameView.text.toString()
        var address = addressView.text.toString()
        var ratingScore = ratingBar.rating

        if(!storeName.isEmpty() && !address.isEmpty() && ratingScore != null ){

            val user = mAuth!!.currentUser
            val uid = user!!.uid

            var store = store_object(storeName,address,ratingScore)
//            Log.i("Review", "create store object")
            mDatabase.child(uid!!).setValue(store)
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this@Review,
                        e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
//            Log.i("Review", " go through add into database")
        }


        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun goToGallery(){
        var intent = Intent(this,Gallery::class.java)
        startActivity(intent)

    }
}