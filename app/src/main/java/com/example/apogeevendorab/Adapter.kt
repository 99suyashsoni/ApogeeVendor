package com.example.apogeevendorab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Adapter(private val menuItems: ArrayList<MenuItem>): RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.menu_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = menuItems.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        p0.itemName.text = menuItems[p1].name
        p0.itemPrice.text = menuItems[p1].price.toString()
        p0.available.text = menuItems[p1].is_available.toString()
    }

    inner class ViewHolder(val view: View) :RecyclerView.ViewHolder(view){

        internal var itemName: TextView = view.findViewById(R.id.textView)
        internal var itemPrice: TextView = view.findViewById(R.id.textView2)
        internal var available: TextView = view.findViewById(R.id.textView3)
    }
}

