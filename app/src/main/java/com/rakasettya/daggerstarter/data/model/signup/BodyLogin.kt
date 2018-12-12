package com.rakasettya.daggerstarter.data.model.signup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BodyLogin {

    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null

    override fun toString(): String {
        return "BodyLogin{" +
                "password = '" + password + '\''.toString() +
                ",email = '" + email + '\''.toString() +
                "}"
    }
}