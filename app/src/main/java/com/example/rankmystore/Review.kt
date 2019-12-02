package com.example.rankmystore
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener
import android.widget.*

class Review  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        var add_Photo_Button = findViewById<View>(R.id.addStorePhotosButton)
        var done_Button = findViewById<View>(R.id.submitButton)
        var storeName = findViewById<View>(R.id.storeName)
        var address = findViewById<View>(R.id.Address)

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
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun goToGallery(){
        var intent = Intent(this,Gallery::class.java)
        startActivity(intent)

    }
}