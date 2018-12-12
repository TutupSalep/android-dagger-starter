package com.rakasettya.daggerstarter.data.model.user

import com.google.gson.annotations.SerializedName

class Role {

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("display_name")
    var displayName: String? = null

    @SerializedName("deleted_at")
    var deletedAt: Any? = null

    override fun toString(): String {
        return "Role{" +
                "updated_at = '" + updatedAt + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",description = '" + description + '\''.toString() +
                ",created_at = '" + createdAt + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",display_name = '" + displayName + '\''.toString() +
                ",deleted_at = '" + deletedAt + '\''.toString() +
                "}"
    }
}