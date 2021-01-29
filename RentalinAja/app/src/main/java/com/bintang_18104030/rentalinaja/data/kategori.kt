package com.bintang_18104030.rentalinaja.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class kategori(
    var id: String? = null,
    var kategori_moblil: String? = null,
    var nama_mobil: String? = null,
    var harga_sewa: String? = null,
    var gambar_mobil: String?= null
) : Parcelable