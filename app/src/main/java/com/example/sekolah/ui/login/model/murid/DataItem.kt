package com.example.sekolah.ui.login.model.murid

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Generated("com.robohorse.robopojogenerator")
data class DataItem(

	@field:SerializedName("id_mrd")
	val idMrd: String? = null,

	@field:SerializedName("kls")
	val kls: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("nama_mrd")
	val namaMrd: String? = null,

	@field:SerializedName("almt")
	val almt: String? = null
):Serializable
//serializable agar dataitem bisa dikirim pakai intens