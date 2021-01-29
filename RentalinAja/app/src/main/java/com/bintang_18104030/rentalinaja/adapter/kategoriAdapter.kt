package com.bintang_18104030.rentalinaja.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bintang_18104030.rentalinaja.R
import com.bintang_18104030.rentalinaja.data.kategori
import com.bintang_18104030.rentalinaja.databinding.ItemKategoriBinding
import com.bintang_18104030.rentalinaja.detailMobil
import com.bintang_18104030.rentalinaja.helper.EXTRA_POSITION
import com.bintang_18104030.rentalinaja.helper.EXTRA_QUOTE
import com.bintang_18104030.rentalinaja.helper.REQUEST_UPDATE


class kategoriAdapter(private val activity: Activity): RecyclerView.Adapter<kategoriAdapter.dataMobilViewHolder>() {
    var listQuotes = ArrayList<kategori>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataMobilViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kategori, parent, false)
        return dataMobilViewHolder(view)
    }


    override fun getItemCount(): Int = this.listQuotes.size
    override fun onBindViewHolder(holder: dataMobilViewHolder, position: Int) {
        holder.bind(listQuotes[position],position)
    }


    inner class dataMobilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemKategoriBinding.bind(itemView)
        fun bind(quote: kategori, position: Int) {
            binding.tvItemTitle.text = quote.nama_mobil

        }
    }
}
