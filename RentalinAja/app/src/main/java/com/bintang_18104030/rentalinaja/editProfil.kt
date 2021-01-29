package com.bintang_18104030.rentalinaja

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bintang_18104030.rentalinaja.databinding.ActivityEditProfilBinding
import com.bintang_18104030.rentalinaja.helper.RESULT_ADD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_edit_profil.*
import java.util.*
import kotlin.collections.HashMap

class editProfil : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityEditProfilBinding
    lateinit var filePath: Uri
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_edit_profil)
        setContentView(binding.root)
        auth = Firebase.auth
        firestore = Firebase.firestore
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@editProfil, loginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnEdit.setOnClickListener(this)

        pilihgambar.setOnClickListener {
            launchGallery()
        }
        uploadGambar.setOnClickListener {
            uploadFile()
        }

    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnEdit -> {
                editUser(
                    binding.inputNama.text.toString(),
                    binding.inputNoTelp.text.toString(),
                    binding.inputAlamat.text.toString())

            }
        }
    }
    private fun editUser(nama: String, noHp: String, alamat: String) {
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
                setResult(RESULT_ADD, intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@editProfil, "Error adding document", Toast.LENGTH_SHORT).show()
            }



    }

    private fun launchGallery() {
        val i = Intent()
        i.setType ("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 111)
    }

    private fun uploadFile() {
        if (filePath!=null){
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()
            //           val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            var imageRef = FirebaseStorage.getInstance().reference.child("imageUser/" + UUID.randomUUID().toString())
            imageRef.putFile(filePath)
                .addOnSuccessListener {p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,"gambar terupload" , Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {p0 ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,p0.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {p0 ->
                    var progress = (100.0 * p0.bytesTransferred)/ p0.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")

                }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            filePath = data.data!!
            var bitmap= MediaStore.Images.Media.getBitmap(contentResolver , filePath)


        }
    }


}