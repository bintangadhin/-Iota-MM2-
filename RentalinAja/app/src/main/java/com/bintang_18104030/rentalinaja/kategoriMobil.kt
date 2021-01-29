package com.bintang_18104030.rentalinaja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintang_18104030.rentalinaja.adapter.kategoriAdapter
import com.bintang_18104030.rentalinaja.data.kategori
import com.bintang_18104030.rentalinaja.databinding.ActivityKategoriMobilBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_kategori_mobil.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class kategoriMobil : AppCompatActivity() {
    private var backPressedTime = 0L
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: kategoriAdapter
    private lateinit var binding: ActivityKategoriMobilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = Firebase.firestore
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser == null) {
            val intent = Intent(this@kategoriMobil, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
        supportActionBar?.title = "Quotes"
        binding.rvKategoriMobil.layoutManager = LinearLayoutManager(this)
        binding.rvKategoriMobil.setHasFixedSize(true)
        adapter = kategoriAdapter(this)

        loadMobil()
        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_nav)
        //Set
        bottomNavigationView.selectedItemId = R.id.kategori
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.beranda -> {
                    startActivity(
                        Intent(
                            applicationContext
                            , menuActivity::class.java
                        )
                    )
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.detail_sewa -> {
                    startActivity(
                        Intent(
                            applicationContext
                            , detailSewa::class.java
                        )
                    )
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profil -> {
                    startActivity(
                        Intent(
                            applicationContext
                            , profilActivity::class.java
                        )
                    )
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.kategori -> return@OnNavigationItemSelectedListener true
            }
            false
        })
    }

    private fun loadMobil() {
        GlobalScope.launch(Dispatchers.Main) {
            progressbar.visibility = View.VISIBLE
            val quotesList = ArrayList<kategori>()
            firestore.collection("mobil").get()
                .addOnSuccessListener { result ->
                    progressbar.visibility = View.INVISIBLE
                    for (document in result) {
                        val id = document.id
                        val kategori_mobil = document.get("kategori_mobil").toString()
                        val nama_mobil = document.get("nama_mobil").toString()
                        val harga_sewa = document.get("harga_sewa").toString()
                        val gambar_mobil= document.get("gambar").toString()
                        quotesList.add(kategori(id, kategori_mobil, nama_mobil, harga_sewa , gambar_mobil))
                    }
                    if (quotesList.size > 0) {
                        binding.rvKategoriMobil.adapter = adapter
                        adapter.listQuotes = quotesList
                    } else {
                        adapter.listQuotes.clear()
                        binding.rvKategoriMobil?.adapter?.notifyDataSetChanged()
                        showSnackbarMessage("Tidak ada data saat ini")
                    }
                }
                .addOnFailureListener { exception ->
                    progressbar.visibility = View.INVISIBLE
                    Toast.makeText(
                        this@kategoriMobil, "Error adding document", Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvKategoriMobil, message, Snackbar.LENGTH_SHORT).show()
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@kategoriMobil, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            Toast.makeText(applicationContext,"tekan kembali untuk keluar", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}