package com.rakasettya.daggerstarter.data.model.signup

import com.google.gson.annotations.SerializedName

data class Success(

	@field:SerializedName("token")
	val token: String? = null
)