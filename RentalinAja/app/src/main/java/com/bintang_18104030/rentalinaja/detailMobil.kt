package com.bintang_18104030.rentalinaja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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
            val imageView: ImageView = binding.gambarMobil.findViewById(R.id.gambar_mobil)

            val url = it.gambar_mobil
            Picasso.get().load(url).into(imageView)
        }!!

    }
}