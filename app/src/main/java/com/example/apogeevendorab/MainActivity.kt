package com.example.apogeevendorab

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(applicationContext)

        val listener = ListenerObject()
        listener.setCustomListener(object: ListenerObject.Listener{
            override fun onDataReceived(menuList: ArrayList<MenuItem>) {

                viewManager = LinearLayoutManager(applicationContext)
                viewAdapter = Adapter(menuList)
                recyclerView.apply {
                    adapter = viewAdapter
                    layoutManager = viewManager
                }
            }
        })

        val database = FirebaseDatabase.getInstance().reference

        //use vendor id instead of vendor - 6 in actual implementation
        database.child("vendors").child("vendor - 6").child("menu").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
                Log.d("Error","Firebase onCancelled called")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var menuList = ArrayList<MenuItem>()
                val iterable = dataSnapshot.children

                for(items in iterable){
                    Log.d("Check","1")
                    Log.d("Check","$items")
                    var menuItemElement = MenuItem(
                        items.child("is_available").value as Boolean,
                        items.child("name").value.toString(),
                        items.child("price").value as Long
                    )

                    Log.d("Element","Check: $menuItemElement")

                    menuList.add(menuItemElement)

                }

                listener.listener!!.onDataReceived(menuList)
            }
        })


    }

}
