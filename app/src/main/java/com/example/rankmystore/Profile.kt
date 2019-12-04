package com.example.rankmystore


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
//import androidx.test.orchestrator.junit.BundleJUnitUtils.getResult
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
//import com.google.firebase.auth.FirebaseAuths.getResult

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
//import jdk.nashorn.internal.runtime.ECMAErrors.getMessage
//import jdk.nashorn.internal.runtime.ECMAException.getException
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import android.net.Uri


class Profile : AppCompatActivity() {
    companion object {
        const val TAG = "Profile"
    }
    lateinit var mDatabase : DatabaseReference
    var user = FirebaseAuth.getInstance().currentUser
    private var mAuth: FirebaseAuth? = null

    var imageView:ImageView? = null


    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var url = ""
        mAuth = FirebaseAuth.getInstance()
//        val horizontalScrollView = HorizontalScrollView(this)
//        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//
//
//        horizontalScrollView.layoutParams = layoutParams
//
//        val linearLayout = LinearLayout(this)
//        val linearParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        linearLayout.orientation = LinearLayout.HORIZONTAL
//        linearLayout.layoutParams = linearParams
//
//        horizontalScrollView.addView(linearLayout)
//
//        val imageView1 = ImageView(this)
//        val params1 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        params1.setMargins(30, 20, 30, 0)
//        params1.gravity = Gravity.CENTER
//        imageView1.layoutParams = params1
//        imageView1.setImageResource(R.drawable.test1)
//        linearLayout.addView(imageView1)
//
//        val imageView2 = ImageView(this)
//        val params2 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        params2.setMargins(0, 20, 30, 0)
//        params2.gravity = Gravity.CENTER
//        imageView2.layoutParams = params2
//        imageView2.setImageResource(R.drawable.test2)
//        linearLayout.addView(imageView2)
//
//        val imageView3 = ImageView(this)
//        val params3 = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        params3.setMargins(0, 20, 30, 0)
//        params3.gravity = Gravity.CENTER
//        imageView3.layoutParams = params3
//        imageView3.setImageResource(R.drawable.test3)
//        linearLayout.addView(imageView3)
//
//        val linearLayout1 = findViewById<LinearLayout>(R.id.horizontalScroll)
//
//        linearLayout1?.addView(horizontalScrollView)

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

//<<<<<<< Updated upstream
//
//            mDatabase.addValueEventListener(object: ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                    for (snapshot in dataSnapshot.children) {
//                        Log.i(TAG, "here1111")
//                        Log.i(TAG, "value of snapshot is " + snapshot)
//                        if (snapshot.key == uid) {
//                            for(ele in snapshot.children){
//                                Log.i(TAG, "value of ele is " + ele)
//                                if(ele.key == "profileImage"){
//                                    Log.i(TAG, "value of current ele is " + ele)
//                                    url = ele.value as String
//                                    Log.i(TAG, "hererereree" + url)
//
//                                    process_the_url(url)
//                                }
//=======
            //retreive image from database
            //start here
//            var imageView = findViewById<ImageView>(R.id.profile_image)

//            var mDatabaseImageRef: DatabaseReference
//            mDatabaseImageRef = mDatabase.child(uid).child("profileImage")
//            var url = mDatabaseImageRef.toString()
//
//            Log.i("find url", "this is url " + url)


//>>>>>>> Stashed changes

//                            }
//
//
//                        }
//                    }
//                }
//
//
//                override fun onCancelled(error: DatabaseError) {
//                    //print error.message
//                }
//            })


            //retreive image from database
            //start here

//          var mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
//            var imageRef = mStorageRef.child(user!!.uid + ".jpg").toString()
//            var link = imageRef.
//
//            Log.i("find url", "this is url " + imageRef)
//
//            Picasso.get().load(imageRef).into(imageView)



            var edit_profile = findViewById<View>(R.id.button_editprofile) as Button
            edit_profile.setOnClickListener{
                val intent = Intent(this, Edit_Profile::class.java)
                startActivity(intent)
            }

            var signout_account = findViewById<View>(R.id.button_signout)
            signout_account.setOnClickListener{sign_out_user()}


        }


        var mainpage_button = findViewById<View>(R.id.button_mainpage)
        mainpage_button.setOnClickListener{
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        var add_Photo_Button = findViewById<View>(R.id.addProfileImage)
        add_Photo_Button.setOnClickListener(){goToAddProfileImage()}

    }
//
//<<<<<<< Updated upstream
//    fun process_the_url(url : String){
//        var imageView = findViewById<ImageView>(R.id.profile_image)
//
//        var mDatabaseImageRef: DatabaseReference
//
//        val profile_url = url
//        var url = profile_url.toString().toUri()
//
//
//        Log.i(TAG, "check this profile url" + profile_url)
//        Log.i(TAG, "check this url" + url)
//=======
    override fun onStart() {
        super.onStart()
        if (user!= null) {
            var uid = user!!.uid

            var imageView = findViewById<ImageView>(R.id.profile_image)
            var mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
            var imageRef = mStorageRef.child(user!!.uid + ".jpg")
            Log.i("find url", "this is url " + imageRef)
            imageRef.downloadUrl.addOnCompleteListener(object : OnCompleteListener<Uri?> {
                override fun onComplete(task: Task<Uri?>) {
                    if (task.isSuccessful()) {
                        Glide.with(this@Profile)
                            .load(task.getResult())
                            .apply(RequestOptions.circleCropTransform())
                            .into(imageView)
                    } else {
                        Toast.makeText(
                            this@Profile,
                            task.getException()!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Firebase id", user!!.uid)
                    }
                }
            }
            )
        }

        }








//        Glide.with(this).load(imageRef).into(imageView)







//        var mDatabaseImageRef: DatabaseReference
//            mDatabase.child(uid).child("profileImage").addValueEventListener(object :ValueEventListener{
//               override fun onCancelled(p0: DatabaseError) {
//                   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//               }
//
//               override fun onDataChange(snapshot: DataSnapshot) {
//                   var link = snapshot.getValue().toString()
//                   imageView = findViewById<ImageView>(R.id.profile_image)
//                   Log.i("find url", "this is url " + link)
//
//                   Picasso.get().load(link).into(imageView)
//
//
//               }
//           })



//>>>>>>> Stashed changes
//
//
//
//
//
//<<<<<<< Updated upstream
//        Picasso.get().load(url).into(imageView)
//    }
//=======
//>>>>>>> Stashed changes

    private fun sign_out_user(){
        mAuth!!.signOut();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun goToAddProfileImage(){
        var intent = Intent(this,AddProfileImage::class.java)
        startActivity(intent)
    }

}

