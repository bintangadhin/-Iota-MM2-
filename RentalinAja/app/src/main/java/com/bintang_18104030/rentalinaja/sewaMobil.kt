package com.bintang_18104030.rentalinaja

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bintang_18104030.rentalinaja.adapter.mobilAdapter
import com.bintang_18104030.rentalinaja.data.dataMobil
import com.bintang_18104030.rentalinaja.databinding.ActivityEditProfilBinding
import com.bintang_18104030.rentalinaja.databinding.ActivitySewaMobilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sewa_mobil.*

class sewaMobil : AppCompatActivity() {
    private lateinit var binding: ActivitySewaMobilBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySewaMobilBinding.inflate(layoutInflater)
     //

        setContentView(binding.root)

        val intent = intent
        val temtitl = intent.getStringExtra("itemtitle")
        val itemcategor = intent.getStringExtra("itemcategori")
        val itemdescriptio = intent.getStringExtra("itemdescription")
        val linkgamba = intent.getStringExtra("linkgambar")

        binding.tvItemTitle.setText(temtitl)
        binding.tvItemCategory.setText(itemcategor)
        binding.tvItemDescription.setText(itemdescriptio)

        val imageView: ImageView = binding.gambarMobil.findViewById(R.id.gambar_mobil)

        val url = linkgamba
        Picasso.get().load(url).into(imageView)


        btnLanjut.setOnClickListener{
            val moveToSewa = Intent(this@sewaMobil, pembayaranActivity::class.java)
            startActivity(moveToSewa)
        }
    }
}