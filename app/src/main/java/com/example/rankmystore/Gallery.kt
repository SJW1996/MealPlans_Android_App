package com.example.rankmystore


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.squareup.picasso.Picasso


class Gallery  : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1


    private var mButtonChooseImage: Button? = null
    private var mButtonUpload: Button? = null

    private var mEditTextFileName: EditText? = null
    private var mImageView: ImageView? = null
    private var mProgressBar: ProgressBar? = null
    private var mImageUri: Uri? = null

    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null

    private var mUploadTask: StorageTask<*>? = null

    var user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        mButtonChooseImage = findViewById(R.id.button_choose_image)
        mButtonUpload = findViewById(R.id.button_upload)

        mEditTextFileName = findViewById(R.id.edit_text_file_name)
        mImageView = findViewById(R.id.image_view)
        mProgressBar = findViewById(R.id.progress_bar)

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference()

        mButtonChooseImage?.setOnClickListener(View.OnClickListener { openFileChooser() })

        mButtonUpload?.setOnClickListener(View.OnClickListener {
            if (mUploadTask != null && mUploadTask!!.isInProgress) {
                Toast.makeText(this@Gallery, "Upload in progress", Toast.LENGTH_SHORT)
                    .show()
            } else {
                uploadFile()
            }
        })
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

    private fun uploadFile() {
        if (mImageUri != null) {
            val fileReference = mStorageRef!!.child(
                user!!.uid + "Store"
                    .toString() + "." + getFileExtension(mImageUri!!)
            )
            mUploadTask = fileReference.putFile(mImageUri!!)
                .addOnSuccessListener { taskSnapshot ->
                    val handler = Handler()
                    handler.postDelayed(Runnable { mProgressBar!!.progress = 0 }, 500)
                    Toast.makeText(this@Gallery, "Upload successful", Toast.LENGTH_LONG)
                        .show()
                    //var name = mEditTextFileName!!.text.toString().trim { it <= ' ' }
                    var url =  taskSnapshot.getStorage().getDownloadUrl().toString()

                    var uid = user!!.uid
//                    mDatabaseRef?.child(uid)?.child("Profile_image")?.setValue(url)
//
                    val intent = Intent(this, Review::class.java)
                    intent.putExtra("EXTRA", url)
                    startActivity(intent)



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

    fun go_To_Review() {
        var intent = Intent(this, Review::class.java)
        startActivity(intent)
    }

}