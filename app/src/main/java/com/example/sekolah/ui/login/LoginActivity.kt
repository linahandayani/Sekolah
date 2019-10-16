package com.example.sekolah.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.example.sekolah.R
import com.example.sekolah.ui.login.model.ResponseLogin
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val btn_login = findViewById(R.id.login)as Button
        btn_login.setOnClickListener{
            masuk(username.text.toString(), password.text.toString())
        }
    }

    private fun masuk(username: String, password: String) {
//        login di ApiService
//callback pilih yang retrofit2
        NetworkModule.getService().login(username, password)
            .enqueue(object : Callback<ResponseLogin> {
//                ora ana koneksi, menangani jika tidak ada koneksi, koneksi internet buruk
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                //500dkk, menangangi jika bisa konek di database namun tidak tampil. ontoh keluar 404, 500, dkk
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                   if (response.isSuccessful){
//                       nampung dikurung siku aja
                       var data=response.body()?.data
//                      ambil data
                       data?.forEach {
                           Toast.makeText(this@LoginActivity, it.nama, Toast.LENGTH_SHORT).show()
                       }
                   }else{

                   }
                }
            })
    }
}
