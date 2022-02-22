package com.shpp.ssierykh.assignment1.model.preferences

interface DataStoreRepository {

    suspend fun putString(key: String, value: String)
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun getString(key: String): String?
    suspend fun getBoolean(key: String): Boolean?
}
