package com.example.sekolah.ui.login.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class DataItem(
//nama value: "nama"
//	sesuai dg response json "nama": "lina"
    //
    @field:SerializedName("nama")
    val nama: String? = null,
//value: "username"
    //	sesuai dg response json "username": "lina",
    @field:SerializedName("username")
    val username: String? = null
)