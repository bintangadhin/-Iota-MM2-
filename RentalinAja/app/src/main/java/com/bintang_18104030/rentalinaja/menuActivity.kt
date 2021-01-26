package com.bintang_18104030.rentalinaja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bintang_18104030.rentalinaja.adapter.mobilAdapter
import com.bintang_18104030.rentalinaja.data.dataMobil
import com.bintang_18104030.rentalinaja.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class menuActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: mobilAdapter
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore
        auth = Firebase.auth

        supportActionBar?.title = "Quotes"
        binding.rvQuotes.layoutManager = LinearLayoutManager(this)
        binding.rvQuotes.setHasFixedSize(true)
        adapter = mobilAdapter(this)

        loadQuotes()
    }

    private fun loadQuotes() {
        GlobalScope.launch(Dispatchers.Main) {
            progressbar.visibility = View.VISIBLE
            val quotesList = ArrayList<dataMobil>()
            firestore.collection("mobil").get()
                .addOnSuccessListener { result ->
                    progressbar.visibility = View.INVISIBLE
                    for (document in result) {
                        val id = document.id
                        val kategori_mobil = document.get("kategori_mobil").toString()
                        val nama_mobil = document.get("nama_mobil").toString()
                        val harga_sewa = document.get("harga_sewa").toString()
                        quotesList.add(dataMobil(id, kategori_mobil, nama_mobil, harga_sewa))
                    }
                    if (quotesList.size > 0) {
                        binding.rvQuotes.adapter = adapter
                        adapter.listQuotes = quotesList
                    } else {
                        adapter.listQuotes.clear()
                        binding.rvQuotes?.adapter?.notifyDataSetChanged()
                        showSnackbarMessage("Tidak ada data saat ini")
                    }
                }
                .addOnFailureListener { exception ->
                    progressbar.visibility = View.INVISIBLE
                    Toast.makeText(
                        this@menuActivity, "Error adding document", Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvQuotes, message, Snackbar.LENGTH_SHORT).show()
    }

}