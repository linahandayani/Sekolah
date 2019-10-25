package com.example.sekolah

import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sekolah.ui.login.model.deleteMurid.ResponseDeleteMurid
import com.example.sekolah.ui.login.model.editMurid.ResponseEditMurid
import com.example.sekolah.ui.login.model.murid.DataItem
import com.example.sekolah.ui.login.model.murid.ResponseMurid
import com.example.sekolah.ui.login.network.NetworkModule
import kotlinx.android.synthetic.main.activity_murid.*
import kotlinx.android.synthetic.main.activity_murid__adapter.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.app_bar_main.*

class Murid : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_murid)

        fm = supportFragmentManager
        ft = fm.beginTransaction()

        NetworkModule.getService().tampilMurid()
            .enqueue(object : Callback<ResponseMurid> {

                override fun onFailure(call: Call<ResponseMurid>, t: Throwable) {

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
                        var adapter = Murid_Adapter(
                            this@Murid,
                            data!!,
                            object : Murid_Adapter.OnMuridClickListener {
                                override fun onClick(list: DataItem, position: Int) {
                                    alert {
                                        positiveButton("Edit") {
                                           val intent = Intent(this@Murid, EditMurid::class.java)
                                            intent.putExtra("murid",list)
                                            startActivity(intent)
                                        }
                                        negativeButton("Hapus") {
                                            deleteMurid(list)
                                        }
                                    }.show()
                                }

                            })
                        recyclerViewMurid.adapter = adapter
                        recyclerViewMurid.layoutManager = LinearLayoutManager(this@Murid)
                    } else {
                    }
                }
            })

    }

    //perhatikan required dan found string jika error: string?= boleh null dan string. tambahkan !!

    private fun deleteMurid(dataItem: DataItem) {
        NetworkModule.getService().deleteMurid(dataItem.idMrd)
            .enqueue(object : Callback<ResponseDeleteMurid> {
                override fun onFailure(call: Call<ResponseDeleteMurid>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseDeleteMurid>,
                    response: Response<ResponseDeleteMurid>
                ) {
                    toast("Data murid berhasil dihapus")
                }

            })
    }

    private fun editMurid(dataItem: DataItem) {
        NetworkModule.getService().editMurid(
            dataItem.idMrd, dataItem.namaMrd, dataItem.gender, dataItem.kls, dataItem.almt
        )
            .enqueue(object : Callback<ResponseEditMurid> {
                override fun onFailure(call: Call<ResponseEditMurid>, t: Throwable) {
                    toast("Data tidak ditemukan")
                }

                override fun onResponse(
                    call: Call<ResponseEditMurid>,
                    response: Response<ResponseEditMurid>
                ) {

                }
            })
    }
}
