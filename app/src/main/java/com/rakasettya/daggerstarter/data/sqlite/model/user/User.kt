package com.rakasettya.daggerstarter.data.sqlite.model.user

import com.google.gson.annotations.SerializedName

class User {

    constructor() {}

    constructor(userableId: String?, roleId: String?, fcmToken: String?, id: String?, displayName: String?, email: String?, username: String?) {
        this.userableId = userableId
        this.roleId = roleId
        this.fcmToken = fcmToken
        this.id = id
        this.displayName = displayName
        this.email = email
        this.username = username
    }

    @SerializedName("userable_id")
    var userableId: String? = null

    @SerializedName("role_id")
    var roleId: String? = null

    @SerializedName("fcm_token")
    var fcmToken: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("display_name")
    var displayName: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("username")
    var username: String? = null

    override fun toString(): String {
        return "User{" +
                "userable_id = '" + userableId + '\''.toString() +
                ",role_id = '" + roleId + '\''.toString() +
                ",fcm_token = '" + fcmToken + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",display_name = '" + displayName + '\''.toString() +
                ",email = '" + email + '\''.toString() +
                ",username = '" + username + '\''.toString() +
                "}"
    }
}