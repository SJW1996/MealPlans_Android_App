package com.example.rankmystore


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.squareup.picasso.Picasso


class Gallery  : AppCompatActivity() {
    companion object {
        const val TAG = "Gallery"
    }
    private val PICK_IMAGE_REQUEST = 1

    private var mAuth: FirebaseAuth? = null
    lateinit var mDatabase : DatabaseReference

    private var mButtonChooseImage: Button? = null
    private var mButtonUpload: Button? = null
    private var mButtonConfirm: Button? = null
    private var mButtonBackMain: Button? = null

    private var mEditTextFileName: EditText? = null
    private var mImageView: ImageView? = null
    private var mProgressBar: ProgressBar? = null
    private var mImageUri: Uri? = null

    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null

    private var mUploadTask: StorageTask<*>? = null

    var user = FirebaseAuth.getInstance().currentUser

    var storeName : String? = ""
    var address : String? = ""
    var ratingScore : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        storeName = intent.getStringExtra("storeName")
        address = intent.getStringExtra("address")
        ratingScore = intent.getStringExtra("ratingScore")


        mButtonChooseImage = findViewById(R.id.button_choose_image)
        mButtonUpload = findViewById(R.id.button_upload)
        mButtonConfirm = findViewById(R.id.button_confirm)

        mButtonBackMain = findViewById(R.id.button_backMain)

        mEditTextFileName = findViewById(R.id.edit_text_file_name)
        mImageView = findViewById(R.id.image_view)
        mProgressBar = findViewById(R.id.progress_bar)

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference()

        mButtonChooseImage?.setOnClickListener(View.OnClickListener { openFileChooser() })

        mButtonBackMain?.setOnClickListener{back_to_main()}

        mButtonConfirm?.setOnClickListener(View.OnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this@Gallery, "Confirm in progress", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Confirm()
            }
        })
//
//


    }

    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null
        ) {
            mImageUri = data.data
            Picasso.get().load(mImageUri).into(mImageView)
        }
    }

    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }




    private fun Confirm() {
        if (mImageUri != null) {

            Log.i(TAG, "storename_in_gallery_uploard: "+ storeName)
            Log.i(TAG, "address_in_gallery_uploard: "+ address)
            Log.i(TAG, "ratingScore_in_gallery_uploard: "+ ratingScore)

            val fileReference = mStorageRef!!.child(
                user!!.uid + storeName
                    .toString() + "." + getFileExtension(mImageUri!!)
            )

            mUploadTask = fileReference.putFile(mImageUri!!)
                .addOnSuccessListener { taskSnapshot ->
                    val handler = Handler()
                    handler.postDelayed(Runnable { mProgressBar!!.progress = 0 }, 500)
                    Toast.makeText(this@Gallery, "Confirm successful", Toast.LENGTH_LONG)
                        .show()
                    //var name = mEditTextFileName!!.text.toString().trim { it <= ' ' }
                    var url =  taskSnapshot.getStorage().getDownloadUrl().toString()

//                    var uid = user!!.uid
//                    mDatabaseRef?.child(uid)?.child("Profile_image")?.setValue(url)
//
//                    val intent = Intent(this, Review::class.java)
//                    intent.putExtra("EXTRA", url)
//                    startActivity(intent)


                    var current_store = store_object(storeName!!, address!!, ratingScore!!.toFloat(), url)

                    mAuth = FirebaseAuth.getInstance()
                    mDatabase = FirebaseDatabase.getInstance().reference
                    val user = mAuth!!.currentUser
                    val uid = user!!.uid
                    val temp_stores = arrayListOf<store_object>()

                    mDatabase.addValueEventListener(object: ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            for (snapshot in dataSnapshot.children) {
                                Log.i(Review.TAG, "here1111")
                                Log.i(Review.TAG, "value of snapshot is " + snapshot)
                                if (snapshot.key == uid) {
                                    val storelist = snapshot.child("storeList")
                                    for(ele in snapshot.children){
                                        Log.i(Review.TAG, "here222")

                                        if(ele.key == "storeList"){
                                            Log.i(Review.TAG, "value of storelist is " + ele)
                                            for(stores in ele.children){
                                                Log.i(Review.TAG, "here333")
                                                Log.i(Review.TAG, "value of store is " + stores)
                                                val copy_store = stores.getValue(store_object::class.java)
                                                if (copy_store != null) {
                                                    Log.i(Review.TAG, "here4444")
                                                    Log.i(Review.TAG, "current store is " + copy_store)
                                                    temp_stores.add(copy_store)
                                                    Log.i(Review.TAG, "here::: " + temp_stores)

                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }


                        override fun onCancelled(error: DatabaseError) {
                            //print error.message
                        }
                    })
//                    uploadFile(temp_stores, current_store, uid)
                    mButtonUpload?.setOnClickListener(){uploadFile(temp_stores, current_store, uid)}



//                    val upload = Upload( name, url)
//                    val uploadId = mDatabaseRef!!.push().key
//                    mDatabaseRef!!.child(uploadId!!).setValue(upload)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this@Gallery,
                        e.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress =
                        100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    mProgressBar!!.progress = progress.toInt()
                }
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show()
        }
    }

    fun uploadFile(temp_stores : ArrayList<store_object>, current_store: store_object, uid:String){

        Log.i(TAG, "temp storelist_in_gallery:: " + temp_stores)
        temp_stores.add(current_store)
        Log.i(TAG, "current storelist_in_gallery:: " + temp_stores)
        mDatabase.child(uid).child("storeList").setValue(temp_stores)

    }

    fun back_to_main(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}