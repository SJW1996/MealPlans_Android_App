package com.example.rankmystore


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_review.*
import org.w3c.dom.Text
import kotlin.collections.arrayListOf

import android.graphics.Color;

import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MapLocation"
    }

    var user = FirebaseAuth.getInstance().currentUser
    lateinit var mDatabase : DatabaseReference
    lateinit var herolist :MutableList<store_object>
    lateinit var listView: ListView
    //lateinit var array: Array<String>
    //array of testing

   // var array = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // mDatabase.child(uid).child("username")
        mDatabase = FirebaseDatabase.getInstance().getReference()
        val adapter_arraylist = ArrayList<String>()
        herolist = mutableListOf()
        listView = findViewById(R.id.stores_list)
        if (user != null){
            var uid = user!!.uid
            //var store_List = mDatabase.child(uid).child("storeList")
            var read_store_list = arrayListOf<String>()


            //var temp_store_array = arrayListOf<String>()


            mDatabase.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (snapshot in snapshot.children) {
                            if (snapshot.key == uid) {
                                //val temp_storelist = snapshot.child("storeList")
                                for (ele in snapshot.children) {
                                    if (ele.key == "storeList") {
                                        Log.i(TAG, "this is element")
                                        for (stores in ele.children) {
                                            var copy_store = stores.getValue(store_object::class.java)
                                            herolist.add(copy_store!!)
                                            //var temp_store_name = copy_store!!.storename
                                            //Log.i(TAG, "this is element 3" + temp_store_name)
                                            //read_store_list.add(temp_store_name)
                                            //herolist.add(temp_store_name)
                                        }
                                        val adapter = store_adapter(applicationContext,R.layout.store_objects,herolist)
                                        listView.adapter = adapter

                                    }
                                }
                                /*
                                for (s in read_store_list) {
                                    Log.i(TAG, "This is strings2222 " + s)
                                    adapter_arraylist.add(s)
                                    Log.i(TAG, s + "added complete")
                                }
                                */
                            }
                        }
                    }
                }

            })
            /*
            for (s in adapter_arraylist){
                Log.i(TAG, "This is string333333" + s)
            }

            */

            /*
            var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth", "Auckland",
                "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")



            val adapter = ArrayAdapter(this,
                R.layout.list_view_item, array)

            val listView:ListView = findViewById(R.id.stores_list)
            listView.setAdapter(adapter)



            val tv_click_me = findViewById(R.id.tv_click_me) as TextView
            // set on-click listener
                tv_click_me.setOnClickListener {
                    // your code to run when the user clicks on the TextView
                    }

            */


        }



        val profileBoutton = findViewById<View>(R.id.button3)
        profileBoutton.setOnClickListener { goToAnActivity() }
        val login_button = findViewById<View>(R.id.button2)
        login_button.setOnClickListener({goToLoginActivity()})

        val storeButton = findViewById<View>(R.id.addStore)
        storeButton.setOnClickListener({goToReviewActivity()})

        //val tempButton = findViewById<View>(R.id.tempButton)
        //tempButton.setOnClickListener({goToTempStoreActivity()})

//        setUpDate()

    }

//    fun setUpDate(){
//        //create a date string.
//        val date_n =
//            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
//                .format(Date())
//        //get hold of textview.
//        val date = findViewById<View>(R.id.Date)  as TextView
//        //set it as current date.
//        date.text = date_n
//    }


    fun goToAnActivity( ) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    fun goToLoginActivity( ) {
        val login_intent = Intent(this, login::class.java)
        startActivity(login_intent)
    }

    fun goToTempStoreActivity( ) {
        val login_intent = Intent(this, Stores::class.java)
        startActivity(login_intent)
    }

    fun goToReviewActivity( ) {
        val store_intent = Intent(this, Review::class.java)
        var login_intent = Intent(this, login::class.java)
        /*
        startActivity(store_intent)

         */
        if(user == null){
            startActivity(login_intent)
        }else{
            startActivity(store_intent)
        }

    }


}


