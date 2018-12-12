package com.rakasettya.daggerstarter.data.model.user

import com.google.gson.annotations.SerializedName

class ResponseUser {

    @SerializedName("data")
    var data: List<DataItem>? = null

    @SerializedName("message")
    var message: Any? = null

    @SerializedName("status")
    var status: Int = 0

    override fun toString(): String {
        return "ResponseUser{" +
                "data = '" + data + '\''.toString() +
                ",message = '" + message + '\''.toString() +
                ",status = '" + status + '\''.toString() +
                "}"
    }
}