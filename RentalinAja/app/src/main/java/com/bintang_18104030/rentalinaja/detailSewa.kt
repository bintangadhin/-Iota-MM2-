package com.bintang_18104030.rentalinaja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class detailSewa : AppCompatActivity() {
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_sewa)


        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_nav)
        //Set
        bottomNavigationView.selectedItemId = R.id.detail_sewa
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
                R.id.kategori -> {
                    startActivity(
                        Intent(
                            applicationContext
                            , kategoriMobil::class.java
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
                R.id.detail_sewa -> return@OnNavigationItemSelectedListener true
            }
            false
        })
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