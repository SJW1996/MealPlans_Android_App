package com.example.rankmystore


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Gravity
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.*

class Stores : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

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



        val tv = findViewById<TextView>(R.id.textView7)

        findViewById<RatingBar>(R.id.ratingBar)?.onRatingBarChangeListener =
            OnRatingBarChangeListener{ _, rating, _ ->
                // Called when the user swipes the RatingBar
                tv.text = getString(R.string.rating_string, rating)
            }

//
//        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
//        if (ratingBar != null) {
//            val button = findViewById<Button>(R.id.rating_submit)
//            button.setOnClickListener{
//                val msg = ratingBar.rating.toString()
//                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//            }
//        }

    }





}
