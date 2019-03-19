package com.example.apogeevendorab

class ListenerObject{

    interface Listener{
        fun onDataReceived(menuList: ArrayList<MenuItem>)
    }

    var listener: Listener? = null

    fun setCustomListener(listener: Listener){

        this.listener = listener
    }
}