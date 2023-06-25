package fr.bendev.seatgeekapp.framework.utils

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * This class handles the CRUD functions for all shared preferences
 */

abstract class BaseSharedPrefs(protected val sharedPrefs: SharedPreferences) {

    /**
     * save(...) function is used to write primitive data in shared preferences
     *
     * @param key The name of the preference to modify.
     * @param item The new value for the preference.
     *
     * Handled types: Boolean, Float, Int, Long, String
     *
     * @throws IllegalArgumentException if the item type does not correspond to primitive types
     * taken by SharedPreferences
     * */
    open fun <T> save(key: String, item: T) {
        sharedPrefs.edit {
            when (item) {
                is Boolean -> putBoolean(key, item)
                is Float -> putFloat(key, item)
                is Int -> putInt(key, item)
                is Long -> putLong(key, item)
                is String -> putString(key, item)
                else -> throw IllegalArgumentException("Type mismatch")
            }
            commit()
        }
    }

    /**
     * load(...) function is used to read primitive data from shared preferences.
     *
     * @param key The name of the preference to modify.
     * @param default Value to return if this preference does not exist.
     *
     * Handled types: Boolean, Float, Int, Long, String.
     *
     * @throws IllegalArgumentException if the item type does not correspond to primitive types
     * taken by SharedPreferences.
     * */
    @Suppress("UNCHECKED_CAST")
    open fun <T> load(key: String, default: T): T {
        return when (default) {
            is Boolean -> sharedPrefs.getBoolean(key, default) as T
            is Float -> sharedPrefs.getFloat(key, default) as T
            is Int -> sharedPrefs.getInt(key, default) as T
            is Long -> sharedPrefs.getLong(key, default) as T
            is String -> sharedPrefs.getString(key, default) as T
            else -> throw IllegalArgumentException()
        }
    }

    /**
     * delete function removes the data from Shared Preferences.
     *
     * @param key Key of the data to removes.
     *
     * */
    open fun delete(key: String) {
        sharedPrefs.edit {
            remove(key)
            commit()
        }
    }

}