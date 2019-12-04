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
import java.net.URL
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso


class Stores : AppCompatActivity(){
    lateinit var mDatabase : DatabaseReference
    var user = FirebaseAuth.getInstance().currentUser
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)
        mDatabase = FirebaseDatabase.getInstance().getReference()

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
        //testing for loading image
        //var url = URL("https://upload.wikimedia.org/wikipedia/commons/3/38/XO_-_2.gif")
        //val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        //imageView1.setImageBitmap(bmp);
        //imageView1.setImageResource(R.drawable.test1)
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

//        val linearLayout1 = findViewById<LinearLayout>(R.id.horizontalScroll)

//        linearLayout1?.addView(horizontalScrollView)


        //val profileBoutton = findViewById<View>(R.id.add_review)
        //profileBoutton.setOnClickListener { goToReviewActivity() }

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
        /*


        mDatabase = FirebaseDatabase.getInstance().getReference()
        val name_text = findViewById<View>(R.id.profile_name) as TextView

        if (user != null){
            var uid = user!!.uid
            mDatabase.child(uid).child("username").addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    name_text.text =  "Welcome " + snapshot.value.toString()
                }
            })
         */

        //read store information from the database
        var store_name_text = findViewById(R.id.textView3) as TextView
        var rating_bar = findViewById(R.id.ratingBar) as RatingBar
        if (user != null) {
            var bundle :Bundle ? =intent.extras
            var real_name = bundle!!.getString("value")
            val real_rating = bundle!!.getFloat("store_score")
            //var strUser: String = intent.getStringExtra("value")
            store_name_text.text = "Store Name: " + real_name
            rating_bar.rating = real_rating
            var url = bundle!!.getString("url")

            var mImageView = findViewById<ImageView>(R.id.profile_image)
            var mStorageRef = FirebaseStorage.getInstance().getReference("uploads")


            var imageRef = mStorageRef.child(user!!.uid + "Store" + ".jpg")
            Log.i("find url", "this is url " + imageRef)
            imageRef.downloadUrl.addOnCompleteListener (object : OnCompleteListener<Uri?> {
                override  fun onComplete(task: Task<Uri?>) {
                    if (task.isSuccessful()) {
                        Glide.with(this@Stores)
                            .load(task.getResult())
                            .apply(RequestOptions.circleCropTransform())
                            .into(mImageView)
                    } else {
                        Toast.makeText(
                            this@Stores,
                            task.getException()!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Firebase id", user!!.uid)
                    }
                }
            }
            )

        }

        var go_back_to_main = findViewById<View>(R.id.button_goback) as Button
        go_back_to_main.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    fun goToReviewActivity( ) {
        val intent = Intent(this, Review::class.java)
        startActivity(intent)
    }




}
