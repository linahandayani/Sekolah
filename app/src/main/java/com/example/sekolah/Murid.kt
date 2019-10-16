package com.example.sekolah

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sekolah.ui.login.model.murid.DataItem
import com.example.sekolah.ui.login.model.murid.ResponseMurid
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_murid.*
import kotlinx.android.synthetic.main.activity_murid__adapter.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Murid : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_murid)

        NetworkModule.getService().tampilMurid()
            .enqueue(object : Callback<ResponseMurid> {

                override fun onFailure(call: Call<ResponseMurid>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<ResponseMurid>,
                    response: Response<ResponseMurid>
                ) {
                    if (response.isSuccessful) {
//                       nampung dikurung siku aja
                        var data = response.body()?.data


//                      ambil data
//                        samakan data, hilangkan tanda tanya di
//                        kalau di adapter, harus di set adapternya, tentunya layoutnya
                        var adapter = Murid_Adapter(this@Murid, data!!, object: Murid_Adapter.OnMuridClickListener {
                            override fun onClick(list: DataItem, position: Int) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                        })
                        recyclerViewMurid.adapter=adapter
                        recyclerViewMurid.layoutManager=LinearLayoutManager(this@Murid)
                    } else {
                    }
                }
            })

    }
}
