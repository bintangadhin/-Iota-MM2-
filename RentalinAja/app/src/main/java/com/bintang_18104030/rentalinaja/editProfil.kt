package com.bintang_18104030.rentalinaja

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bintang_18104030.rentalinaja.databinding.ActivityEditProfilBinding
import com.bintang_18104030.rentalinaja.helper.RESULT_ADD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class editProfil : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityEditProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@editProfil, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnEdit.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnEdit -> {
                signIn(
                    binding.inputNama.text.toString(),
                    binding.inputNoTelp.text.toString(),
                    binding.inputAlamat.text.toString())
            }

        }
    }
    private fun signIn(nama: String, noHp: String, alamat: String) {
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid

        val db = FirebaseFirestore.getInstance()
        val dataprofil : MutableMap<String , Any> =  HashMap()
        dataprofil["uid"] = currentuser
        dataprofil["nama"] = nama
        dataprofil["noHp"] = noHp
        dataprofil["alamat"] = alamat

        db.collection("dataProfil").document(currentuser)
            .set(dataprofil)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this@editProfil,
                    "DocumentSnapshot added with ID: ",
                    Toast.LENGTH_SHORT).show()
                setResult(RESULT_ADD, intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@editProfil, "Error adding document", Toast.LENGTH_SHORT).show()
            }



    }


}