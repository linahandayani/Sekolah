package com.example.sekolah.ui.login.network

import com.example.sekolah.ui.login.model.ResponseLogin
import com.example.sekolah.ui.login.model.murid.ResponseMurid
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//isi api server adl function yang digunakan utk request ke server
interface ApiService {
//GET sesuaikan dengan function di controllerCI
//    get bisa test di browser, post tidak
    @GET("login_json")
//value: harus sama persis dengan yg ada di get (controller),
// function: login_json, username:String itu parameter
//parameter: menampung yg akan dikirim
//response Call retrofit 2, jika merah di ResponLogin
    fun login(@Query("username") username:String,
              @Query("password") password:String): Call <ResponseLogin>

    @GET ("tampil_murid")
    fun tampilMurid():Call<ResponseMurid>
}