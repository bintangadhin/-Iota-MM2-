package com.bintang_18104030.rentalinaja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bintang_18104030.rentalinaja.adapter.mobilAdapter
import com.bintang_18104030.rentalinaja.data.dataMobil
import com.bintang_18104030.rentalinaja.databinding.ActivityDetailMobilBinding
import com.bintang_18104030.rentalinaja.helper.EXTRA_QUOTE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_mobil.*
import kotlinx.android.synthetic.main.activity_login.*


class detailMobil : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: mobilAdapter
    private lateinit var binding: ActivityDetailMobilBinding
    private var quote: dataMobil? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore
        auth = Firebase.auth

        quote = intent.getParcelableExtra(EXTRA_QUOTE)

        quote?.let {
            binding.tvItemTitle.setText(it.kategori_moblil)
            binding.tvItemCategory.setText(it.nama_mobil)
            binding.tvItemDescription.setText(it.harga_sewa)
            binding.tvRating.setText(it.rating)
            binding.tvLiter.setText(it.liter)
            binding.tvOrang.setText(it.orang)
            binding.tvTipeMesin.setText(it.mesin)
            binding.tvTipeMobil.setText(it.tipe_mobil)
            binding.tvLink.setText(it.gambar_mobil)

            val imageView: ImageView = binding.gambarMobil.findViewById(R.id.gambar_mobil)

            val url = it.gambar_mobil
            Picasso.get().load(url).into(imageView)
        }!!

        btnSewa.setOnClickListener{
            val item_title = binding.tvItemTitle.text.toString()
            val item_categori = binding.tvItemCategory.text.toString()
            val item_description = binding.tvItemDescription.text.toString()
            val link_gambar = binding.tvLink.text.toString()
            val intent = Intent(this@detailMobil, sewaMobil::class.java)
            intent.putExtra("itemtitle", item_title)
            intent.putExtra("itemcategori", item_categori)
            intent.putExtra("itemdescription", item_description)
            intent.putExtra("linkgambar", link_gambar)
            startActivity(intent)
        }

    }




}