package com.example.rankmystore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_review.*

class Review  : AppCompatActivity(){
    companion object {
        const val TAG = "MapLocation"
    }

    private var mAuth: FirebaseAuth? = null
    lateinit var mDatabase : DatabaseReference
    lateinit var addrText : EditText
    lateinit var storenameText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        addrText = findViewById<EditText>(R.id.Address)
        storenameText = findViewById<EditText>(R.id.storeName)

        var address = addrText.text.toString()
        var storename = storenameText.text.toString()


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



//        val user = mAuth!!.currentUser
//        val uid = user!!.uid
//        val rating_value = findViewById<RatingBar>(R.id.ratingBar).rating


        done_Button.setOnClickListener(){finishAddPhotos()}
        add_Photo_Button.setOnClickListener(){goToGallery()}

        var show_map_button = findViewById<View>(R.id.button4)
        show_map_button.setOnClickListener{processClick()}



    }

    fun finishAddPhotos(){
        var storeNameView = findViewById<View>(R.id.storeName) as EditText
        var addressView = findViewById<View>(R.id.Address) as EditText
        var ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar

        var storeName = storeNameView.text.toString()
        var address = addressView.text.toString()
        var ratingScore = ratingBar.rating

        if(!storeName.isEmpty() && !address.isEmpty()){

            val user = mAuth!!.currentUser
            val uid = user!!.uid

            var current_store = store_object(storeName, address, ratingScore)

            val stores = arrayListOf<store_object>()

            // retrieve store list from database, and update it
            mDatabase.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (snapshot in dataSnapshot.children) {
                        val storelist = snapshot.child(uid).child("storeList").getValue(arrayListOf<store_object>()::class.java)
                        if(storelist == null){

                        }else{
                            for(store in storelist){
                                if(store == null){

                                }else{
                                    stores.add(store)
                                }
                            }
                        }


                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    //print error.message
                }
            })

            stores.add(current_store)
            mDatabase.child(uid).child("storeList").setValue(stores)
//            Log.i("Review", "create store object")
//            mDatabase.child(uid).setValue(store)
//                .addOnFailureListener { e ->
//                    Toast.makeText(
//                        this@Review,
//                        e.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            Log.i("Review", " go through add into database")




        }


        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun goToGallery(){
        var intent = Intent(this,Gallery::class.java)
        startActivity(intent)

    }
    private fun processClick(){
        try {
            var address = addrText.text.toString()
            address = address.replace(' ', '+')

            val geoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$address"))
            if (packageManager.resolveActivity(geoIntent,0) != null){
                startActivity(geoIntent)
            }
        }catch (e: Exception){
            Log.e(TAG,e.toString())
        }
    }
    override fun onStart() {
        super.onStart()
        Log.i(TAG, "The activity is visible and about to be started.")
    }


    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "The activity is visible and about to be restarted.")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "The activity is visible and has focus (it is now \"resumed\")")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"Another activity is taking focus (this activity is about to be \"paused\")")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "The activity is about to be destroyed.")
    }
}