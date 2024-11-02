package com.example.navigationcomponents

object Constant {
    private lateinit var datalist:ArrayList<model>
    fun getdata():ArrayList<model>{
        datalist= ArrayList<model>()
        for(i in 0..20){
            datalist.add(model("Notification $i"))
        }
        return datalist
    }
}