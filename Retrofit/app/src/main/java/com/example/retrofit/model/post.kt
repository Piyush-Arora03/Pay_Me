package com.example.retrofit.model

class post(
        var userId: Int,
        var id: Int,
        var title: String,
        var body: String
    ){
    constructor(userId: Int, title: String, body: String) : this(userId,0,title,body) {
    }
}