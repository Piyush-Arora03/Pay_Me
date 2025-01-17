package com.example.retrofit

import com.example.retrofit.model.data
import com.example.retrofit.model.post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface MyWebServices {
    companion object{
        const val FEED="posts"
    }
   @GET(FEED)
   fun getposts() : Call<List<post>>

//   @GET("posts/{id}/comments") //{id}-> replacement block
//   fun getcomments(@Path("id") postId:Int) : Call<List<data>>

   //query oarameters
   @GET("comments") //comments?postId=1 -> ?,= retrofit laga dega
   fun getcomments(@Query("postId") postId: Array<Int>,
                   @Query("_sort") sortby:String,
                   @Query("_order") orderby:String) : Call<List<data>>
//query map
   @GET("comments")
   fun getcomments(@QueryMap params:Map<String,String>) : Call<List<data>>

   @GET
   fun getcomments(@Url url:String) : Call<List<data>>

   @POST(FEED)
   fun CreatePost(@Body Post: post) : Call<post>

   @FormUrlEncoded
   @POST(FEED)
   fun CreatePost(@Field("userId") userid:Int,
                  @Field("title") title:String,
                  @Field("body") body:String) : Call<post> //key value pair bhejte hai

   @FormUrlEncoded
   @POST(FEED)
   fun CreatePost(@FieldMap fields:Map<String,String>) : Call<post>

}