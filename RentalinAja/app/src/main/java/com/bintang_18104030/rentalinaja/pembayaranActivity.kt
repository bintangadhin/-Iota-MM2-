package com.bintang_18104030.rentalinaja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bintang_18104030.rentalinaja.databinding.ActivityPembayaranBinding
import kotlinx.android.synthetic.main.activity_sewa_mobil.*

class pembayaranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnLanjut.setOnClickListener{
            val moveToSewa = Intent(this@pembayaranActivity, menuActivity::class.java)
            startActivity(moveToSewa)
        }
    }
}