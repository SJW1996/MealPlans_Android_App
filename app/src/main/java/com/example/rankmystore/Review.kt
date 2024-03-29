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
import kotlin.collections.arrayListOf


class Review  : AppCompatActivity(){
    companion object {
        const val TAG = "Review"
    }

    private var mAuth: FirebaseAuth? = null
    lateinit var mDatabase : DatabaseReference


    lateinit var addrText : EditText
    lateinit var storenameText: EditText

    var storeName : String? = ""
    var address : String? = ""
    var ratingScore : Float? = -1f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        var storeNameView = findViewById<View>(R.id.storeName) as EditText
        var addressView = findViewById<View>(R.id.Address) as EditText
        var ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar

        var storeName = storeNameView.text.toString()
        var address = addressView.text.toString()
        var ratingScore = ratingBar.rating.toString()

        addrText = findViewById<EditText>(R.id.Address)
//        storenameText = findViewById<EditText>(R.id.storeName)
//
//        var address = addrText.text.toString()
//        var storename = storenameText.text.toString()


//        mAuth = FirebaseAuth.getInstance()
//        mDatabase = FirebaseDatabase.getInstance().reference
//        val user = mAuth!!.currentUser
//        val uid = user!!.uid
//        val temp_stores = arrayListOf<store_object>()
//
//        mDatabase.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                for (snapshot in dataSnapshot.children) {
//                    Log.i(TAG, "here1111")
//                    Log.i(TAG, "value of snapshot is " + snapshot)
//                    if (snapshot.key == uid) {
//                        val storelist = snapshot.child("storeList")
//                        for(ele in snapshot.children){
//                            Log.i(TAG, "here222")
//
//                            if(ele.key == "storeList"){
//                                Log.i(TAG, "value of storelist is " + ele)
//                                for(stores in ele.children){
//                                    Log.i(TAG, "here333")
//                                    Log.i(TAG, "value of store is " + stores)
//                                    val copy_store = stores.getValue(store_object::class.java)
//                                    if (copy_store != null) {
//                                        Log.i(TAG, "here4444")
//                                        Log.i(TAG, "current store is " + copy_store)
//                                        temp_stores.add(copy_store)
//                                        Log.i(TAG, "here::: " + temp_stores)
//
//                                    }
//                                }
//                            }
//                        }
//                        Log.i(TAG, "here555")
//
//                    }
//                }
//            }
//
//
//            override fun onCancelled(error: DatabaseError) {
//                //print error.message
//            }
//        })


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


        var show_map_button = findViewById<View>(R.id.button4)

        show_map_button.setOnClickListener{processClick()}

        done_Button.setOnClickListener(){finishAddPhotos()}
        add_Photo_Button.setOnClickListener(){goToGallery()}






    }

    fun finishAddPhotos(){



        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun goToGallery(){
        var storeNameView = findViewById<View>(R.id.storeName) as EditText
        var addressView = findViewById<View>(R.id.Address) as EditText
        var ratingBar = findViewById<View>(R.id.ratingBar) as RatingBar

        storeName = storeNameView.text.toString()
        address = addressView.text.toString()
        var ratingScore_string = ratingBar.rating.toString()



        Log.i(TAG, "storename: "+ storeName)
        Log.i(TAG, "address: "+ address)
        Log.i(TAG, "ratingScore: "+ ratingScore_string)

        var intent = Intent(this,Gallery::class.java)
        intent.putExtra("storeName", storeName)
        intent.putExtra("address", address)
        intent.putExtra("ratingScore", ratingScore_string)
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