package com.pr7.jc_biztaxi_v4.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager constructor(val context: Context) {
    suspend fun save(key:String,value:String) {
        val datastorekey= stringPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[datastorekey]=value
        }
    }
    suspend fun load(key:String): Flow<String> {
        val datastorekey= stringPreferencesKey(key)
        val flow: Flow<String> =context.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[datastorekey]?:""
            }

        return flow
    }
}