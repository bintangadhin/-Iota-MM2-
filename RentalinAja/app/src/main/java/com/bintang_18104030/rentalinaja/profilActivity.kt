package com.bintang_18104030.rentalinaja

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.bintang_18104030.rentalinaja.databinding.ActivityProfilBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profil.*
import java.util.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
class profilActivity : AppCompatActivity() , View.OnClickListener{

    private var backPressedTime = 0L
    private lateinit var binding: ActivityProfilBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)
        firestore = Firebase.firestore


        binding.tvLogout.setOnClickListener(this)

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@profilActivity, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            updateUI(currentUser)
        }

        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottom_nav)
        //Set
        bottomNavigationView.selectedItemId = R.id.profil
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.beranda -> {
                    startActivity(
                        Intent(applicationContext, menuActivity::class.java))
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.kategori -> {
                    startActivity(Intent(applicationContext, kategoriMobil::class.java))
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.detail_sewa -> {
                    startActivity(Intent( applicationContext, detailSewa::class.java))
                    finish()
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profil -> return@OnNavigationItemSelectedListener true
            }
            false
        })

        tv_edit_profil.setOnClickListener{
            val moveTo = Intent(this@profilActivity, editProfil::class.java)
            startActivity(moveTo)
        }
        tv_edit_profil_1.setOnClickListener{
            val moveTo = Intent(this@profilActivity, editProfil::class.java)
            startActivity(moveTo)
        }
        tv_pengaturan.setOnClickListener{
            val moveTo = Intent(this@profilActivity, pengaturanActivity::class.java)
            startActivity(moveTo)
        }
        tv_lokasi.setOnClickListener{
            val intent = Intent(this, MapsFragment::class.java)
            startActivity(intent)
        }



    }
    private fun updateUI(currentUser: FirebaseUser) {
        currentUser?.let {

            val email = currentUser.email
            val uid = currentUser.uid
            val namauser = tv_username.text.toString()

            binding.tvEmail.text = email
            for (profile in it.providerData) {
            }
        }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@profilActivity, loginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            updateUI(currentUser)
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_logout -> {
                signOut()
            }

        }
    }



    private fun signOut() {
        auth.signOut()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@profilActivity, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}




