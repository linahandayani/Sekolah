package com.example.sekolah

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.sekolah.ui.login.model.murid.DataItem
import kotlinx.android.synthetic.main.activity_murid__adapter.view.*

//primary konstruktor di class utama
class Murid_Adapter(
    var context: Context,
    var list: List<DataItem>,
    private val onItemClickListener: OnMuridClickListener
) :
    RecyclerView.Adapter<Murid_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View

        v = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_murid__adapter, parent, false)
//       perhatikan jumlah parameter di ViewHolder
//        1.
        return ViewHolder(v, onItemClickListener)
//        return ViewHolder(
//            context,
//            LayoutInflater.from(context).inflate(R.layout.activity_murid__adapter, parent, false),
//            onItemClickListener
//        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        //depan itunama variabel di ViewHolder, belakang harus sama dengan val/konstanta di DataItemnya Murid
        holder.idMurid.text = item.idMrd
        holder.namaMurid.text = item.namaMrd
        holder.jk.text = item.gender
        holder.kelas.text = item.kls
        holder.alamat.text = item.almt

        holder.onBindviewMurid(list[position], position)
    }

    class ViewHolder(
        itemView: View,
        private val onItemClickListener: OnMuridClickListener
    ) :
        RecyclerView.ViewHolder(itemView) {
        var idMurid = itemView.tv_id_murid
        var namaMurid = itemView.tv_nama_murid
        var jk = itemView.tv_jk
        var kelas = itemView.tv_kelas
        var alamat = itemView.tv_alamat

        fun onBindviewMurid(
            list: DataItem,
            position: Int
        ) {
            itemView.setOnClickListener {
                onItemClickListener.onClick(list, position)
            }

        }

        //langkah membuat linearLayout bisa di

    }
//2.
    interface OnMuridClickListener {
        fun onClick(list: DataItem, position: Int)
    }


}
