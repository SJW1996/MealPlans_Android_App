package com.example.rankmystore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_review.*
import kotlin.collections.arrayListOf



class store_adapter(val mCtx: Context, val layoutResId: Int, val store_list: List<store_object>)
    :ArrayAdapter<store_object>(mCtx,layoutResId,store_list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        val LayoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = LayoutInflater.inflate(layoutResId,null)
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val store = store_list[position]

        textViewName.text = store.storename
        textViewName.setOnClickListener {
            val intent = Intent(mCtx, Stores::class.java)
            var store_name_str = ""
            intent.putExtra("key", "Kotlin")
            intent.putExtra("value", store.storename)
            mCtx.startActivity(intent)
        }
        return view
    }

}
