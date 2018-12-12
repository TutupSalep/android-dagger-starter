package com.rakasettya.daggerstarter.data.model.user

import com.google.gson.annotations.SerializedName

class DataItem {

    @SerializedName("role")
    var role: Role? = null

    @SerializedName("userable_id")
    var userableId: Int = 0

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("role_id")
    var roleId: Int = 0

    @SerializedName("fcm_token")
    var fcmToken: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("display_name")
    var displayName: String? = null

    @SerializedName("deleted_at")
    var deletedAt: Any? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("userable_type")
    var userableType: String? = null

    override fun toString(): String {
        return "DataItem{" +
                "role = '" + role + '\''.toString() +
                ",userable_id = '" + userableId + '\''.toString() +
                ",updated_at = '" + updatedAt + '\''.toString() +
                ",role_id = '" + roleId + '\''.toString() +
                ",fcm_token = '" + fcmToken + '\''.toString() +
                ",created_at = '" + createdAt + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",display_name = '" + displayName + '\''.toString() +
                ",deleted_at = '" + deletedAt + '\''.toString() +
                ",email = '" + email + '\''.toString() +
                ",username = '" + username + '\''.toString() +
                ",userable_type = '" + userableType + '\''.toString() +
                "}"
    }
}