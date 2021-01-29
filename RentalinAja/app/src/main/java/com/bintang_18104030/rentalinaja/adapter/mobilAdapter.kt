package com.bintang_18104030.rentalinaja.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bintang_18104030.rentalinaja.R
import com.bintang_18104030.rentalinaja.data.dataMobil
import com.bintang_18104030.rentalinaja.databinding.ItemMobilBinding
import com.bintang_18104030.rentalinaja.detailMobil
import com.bintang_18104030.rentalinaja.helper.EXTRA_POSITION
import com.bintang_18104030.rentalinaja.helper.EXTRA_QUOTE
import com.bintang_18104030.rentalinaja.helper.REQUEST_UPDATE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_onboarding_fragments.view.*


class mobilAdapter(private val activity: Activity): RecyclerView.Adapter<mobilAdapter.dataMobilViewHolder>() {
    var listQuotes = ArrayList<dataMobil>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataMobilViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mobil, parent, false)
        return dataMobilViewHolder(view)
    }


    override fun getItemCount(): Int = this.listQuotes.size
    override fun onBindViewHolder(holder: dataMobilViewHolder, position: Int) {
        holder.bind(listQuotes[position],position)
    }


    inner class dataMobilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMobilBinding.bind(itemView)
        fun bind(quote: dataMobil, position: Int) {
            binding.tvItemTitle.text = quote.kategori_moblil
            binding.tvItemCategory.text = quote.nama_mobil
            binding.tvItemDescription.text = quote.harga_sewa
            val imageView: ImageView = binding.gambarMobil.findViewById(R.id.gambar_mobil)

            val url = quote.gambar_mobil
            Picasso.get().load(url).into(imageView)

            binding.cvItemQuote.setOnClickListener{
                val intent = Intent(activity, detailMobil::class.java )
                intent.putExtra(EXTRA_POSITION, position)
                intent.putExtra(EXTRA_QUOTE, quote)
                activity.startActivityForResult(intent, REQUEST_UPDATE)
            }
        }
    }
}
