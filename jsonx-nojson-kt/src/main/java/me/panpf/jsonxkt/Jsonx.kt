/*
 * Copyright (C) 2019 Peng fei Pan <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package me.panpf.jsonxkt

import me.panpf.jsonx.Jsonx
import me.panpf.jsonx.ToBean
import me.panpf.jsonx.ToJsonObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/* ************************************* check ***************************************** */


/**
 * If the given json string is empty, for empty example: ' ', 'null', '{}', '[]' returns true
 */
inline fun String?.isEmptyJson(): Boolean = Jsonx.isEmpty(this)

/**
 * If the given json string is not empty, for empty example: ' ', 'null', '{}', '[]' returns true
 */
inline fun String?.isNotEmptyJson(): Boolean = Jsonx.isNotEmpty(this)

/**
 * If the given json string is empty, for empty example: ' ', 'null', '{}' returns true
 */
inline fun String?.isEmptyJsonObject(): Boolean = Jsonx.isEmptyObject(this)

/**
 * If the given json string is not empty, for empty example: ' ', 'null', '{}' returns true
 */
inline fun String?.isNotEmptyJsonObject(): Boolean = Jsonx.isNotEmptyObject(this)

/**
 * If the given json string is empty, for empty example: ' ', 'null', '[]' returns true
 */
inline fun String?.isEmptyJsonArray(): Boolean = Jsonx.isEmptyArray(this)

/**
 * If the given json string is not empty, for empty example: ' ', 'null', '[]' returns true
 */
inline fun String?.isNotEmptyJsonArray(): Boolean = Jsonx.isNotEmptyArray(this)

/**
 * Return true if the given json string is a json object
 */
inline fun String?.isJsonObject(): Boolean = Jsonx.isObject(this)

/**
 * Return true if the given json string is not a json object
 */
inline fun String?.isNotJsonObject(): Boolean = Jsonx.isNotObject(this)

/**
 * Return true if the given json string is a json array
 */
inline fun String?.isJsonArray(): Boolean = Jsonx.isArray(this)

/**
 * Return true if the given json string is not a json array
 */
inline fun String?.isNotJsonArray(): Boolean = Jsonx.isNotArray(this)


/* ************************************* toJsonObject ***************************************** */

/**
 * Convert a string of to a JSONObject
 */
@Throws(JSONException::class)
inline fun String?.toJsonObject(): JSONObject? = Jsonx.toJsonObject(this)

/**
 * Convert a object of to a JSONObject
 */
@Throws(JSONException::class)
inline fun <T> T.toJsonObject(toJsonObject: ToJsonObject<T>): JSONObject? = Jsonx.toJsonObject(this, toJsonObject)


/* ************************************* toJsonArray ***************************************** */

/**
 * Convert a string of to a JSONArray
 */
@Throws(JSONException::class)
inline fun String?.toJsonArray(): JSONArray? = Jsonx.toJsonArray(this)

/**
 * Convert a list of to a JSONArray
 */
@Throws(JSONException::class)
inline fun <T> List<T>?.toJsonArray(toJsonObject: ToJsonObject<T>): JSONArray? = Jsonx.toJsonArray(this, toJsonObject)

/**
 * Convert a list of to a JSONArray
 */
inline fun <T> List<T>?.toJsonArray(): JSONArray? = Jsonx.toJsonArray(this)

/**
 * Convert a array of to a JSONArray
 */
@Throws(JSONException::class)
inline fun <T> Array<T>?.toJsonArray(toJsonObject: ToJsonObject<T>): JSONArray? = Jsonx.toJsonArray(this, toJsonObject)

/**
 * Convert a array of to a JSONArray
 */
inline fun <T> Array<T>?.toJsonArray(): JSONArray? = Jsonx.toJsonArray(this)

/**
 * Convert a array of int to a JSONArray
 */
inline fun IntArray?.toJsonArray(): JSONArray? = Jsonx.toJsonArray(this)

/**
 * Convert a array of double to a JSONArray
 */
@Throws(JSONException::class)
inline fun DoubleArray?.toJsonArray(): JSONArray? = Jsonx.toJsonArray(this)

/**
 * Convert a array of long to a JSONArray
 */
inline fun LongArray?.toJsonArray(): JSONArray? = Jsonx.toJsonArray(this)

/**
 * Convert a array of boolean to a JSONArray
 */
inline fun BooleanArray?.toJsonArray(): JSONArray? = Jsonx.toJsonArray(this)


/**
 * Convert a list of to a JSONArray string
 */
@Throws(JSONException::class)
inline fun <T> List<T>?.toJsonArrayString(toJsonObject: ToJsonObject<T>): String? = Jsonx.toJsonArrayString(this, toJsonObject)

/**
 * Convert a array of to a JSONArray string
 */
@Throws(JSONException::class)
inline fun <T> Array<T>?.toJsonArrayString(toJsonObject: ToJsonObject<T>): String? = Jsonx.toJsonArrayString(this, toJsonObject)

/**
 * Convert a list of to a JSONArray string
 */
inline fun <T> List<T>?.toJsonArrayString(): String? = Jsonx.toJsonArrayString(this)

/**
 * Convert a array of to a JSONArray string
 */
inline fun <T> Array<T>?.toJsonArrayString(): String? = Jsonx.toJsonArrayString(this)

/**
 * Convert a array of int to a JSONArray string
 */
inline fun IntArray?.toJsonArrayString(): String? = Jsonx.toJsonArrayString(this)

/**
 * Convert a array of double to a JSONArray string
 */
@Throws(JSONException::class)
inline fun DoubleArray?.toJsonArrayString(): String? = Jsonx.toJsonArrayString(this)

/**
 * Convert a array of long to a JSONArray string
 */
inline fun LongArray?.toJsonArrayString(): String? = Jsonx.toJsonArrayString(this)

/**
 * Convert a array of boolean to a JSONArray string
 */
inline fun BooleanArray?.toJsonArrayString(): String? = Jsonx.toJsonArrayString(this)


/* ************************************* toBean ***************************************** */


/**
 * Convert JSONArray to the Bean
 */
@Throws(JSONException::class)
inline fun <Bean> JSONObject?.toBean(toBean: ToBean<Bean>): Bean? = Jsonx.toBean(this, toBean)

/**
 * Convert json to the Bean
 */
@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBean(toBean: ToBean<Bean>): Bean? = Jsonx.toBean(this, toBean)


/**
 * Convert JSONArray to the Bean list
 */
@Throws(JSONException::class)
inline fun <Bean> JSONArray?.toBeanList(toBean: ToBean<Bean>): ArrayList<Bean>? = Jsonx.toBeanList(this, toBean)

/**
 * Convert json to the Bean list
 */
@Throws(JSONException::class)
inline fun <Bean> String?.jsonToBeanList(toBean: ToBean<Bean>): ArrayList<Bean>? = Jsonx.toBeanList(this, toBean)


/**
 * Convert a JSONArray to a String list
 */
@Throws(JSONException::class)
inline fun JSONArray?.toStringList(): List<String>? = Jsonx.toStringList(this)

/**
 * Convert a json to a String list
 */
@Throws(JSONException::class)
inline fun String?.jsonToStringList(): List<String>? = Jsonx.toStringList(this)


/**
 * Convert a JSONArray to a String array
 */
@Throws(JSONException::class)
inline fun JSONArray?.toStringArray(): Array<String>? = Jsonx.toStringArray(this)

/**
 * Convert a json to a String array
 */
@Throws(JSONException::class)
inline fun String?.jsonToStringArray(): Array<String>? = Jsonx.toStringArray(this)


/**
 * Convert a JSONArray to a int array
 */
@Throws(JSONException::class)
inline fun JSONArray?.toIntArray(): IntArray? = Jsonx.toIntArray(this)

/**
 * Convert a json to a int array
 */
@Throws(JSONException::class)
inline fun String?.jsonToIntArray(): IntArray? = Jsonx.toIntArray(this)


/**
 * Convert a JSONArray to a double array
 */
@Throws(JSONException::class)
inline fun JSONArray?.toDoubleArray(): DoubleArray? = Jsonx.toDoubleArray(this)

/**
 * Convert a json to a double array
 */
@Throws(JSONException::class)
inline fun String?.jsonToDoubleArray(): DoubleArray? = Jsonx.toDoubleArray(this)


/**
 * Convert a JSONArray to a long array
 */
@Throws(JSONException::class)
inline fun JSONArray?.toLongArray(): LongArray? = Jsonx.toLongArray(this)

/**
 * Convert a json to a long array
 */
@Throws(JSONException::class)
inline fun String?.jsonToLongArray(): LongArray? = Jsonx.toLongArray(this)


/**
 * Convert a JSONArray to a boolean array
 */
@Throws(JSONException::class)
inline fun JSONArray?.toBooleanArray(): BooleanArray? = Jsonx.toBooleanArray(this)

/**
 * Convert a json to a boolean array
 */
@Throws(JSONException::class)
inline fun String?.jsonToBooleanArray(): BooleanArray? = Jsonx.toBooleanArray(this)


/* ************************************* opt and get ***************************************** */


/**
 * Returns the value mapped by keys
 */
inline fun JSONObject?.opt(keys: Array<String>): Any? = Jsonx.opt(this, keys)


/**
 * Returns the value mapped by keys and turn it into a string, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optString(keys: Array<String>, defaultValue: String = ""): String = Jsonx.optString(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a int, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optInt(keys: Array<String>, defaultValue: Int = 0): Int = Jsonx.optInt(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a long, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optLong(keys: Array<String>, defaultValue: Long = 0L): Long = Jsonx.optLong(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a boolean, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optBoolean(keys: Array<String>, defaultValue: Boolean = false): Boolean = Jsonx.optBoolean(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a double, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optDouble(keys: Array<String>, defaultValue: Double = 0.0): Double = Jsonx.optDouble(this, keys, defaultValue)

/**
 * Returns the value mapped by keys, or null if no such mapping exists.
 */
inline fun JSONObject?.optJSONObject(keys: Array<String>): JSONObject? = Jsonx.optJSONObject(this, keys)

/**
 * Returns the value mapped by keys, or null if no such mapping exists.
 */
inline fun JSONObject?.optJSONArray(keys: Array<String>): JSONArray? = Jsonx.optJSONArray(this, keys)


/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.get(keys: Array<String>): Any = Jsonx.get(this, keys)


/**
 * Returns the value mapped by keys if it exists and turn it into a string, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getString(keys: Array<String>): String = Jsonx.getString(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a int, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getInt(keys: Array<String>): Int = Jsonx.getInt(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a long, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getLong(keys: Array<String>): Long = Jsonx.getLong(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a boolean, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getBoolean(keys: Array<String>): Boolean = Jsonx.getBoolean(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a double, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getDouble(keys: Array<String>): Double = Jsonx.getDouble(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getJSONArray(keys: Array<String>): JSONArray = Jsonx.getJSONArray(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject?.getJSONObject(keys: Array<String>): JSONObject = Jsonx.getJSONObject(this, keys)


/* ************************************* format ***************************************** */


/**
 * Formatted output for easy viewing
 */
inline fun JSONObject?.format(): String = Jsonx.format(this)

/**
 * Formatted output for easy viewing
 */
inline fun JSONArray?.format(): String = Jsonx.format(this)

/**
 * Formatted output for easy viewing
 */
@Throws(JSONException::class)
inline fun String?.formatJson(): String = Jsonx.format(this)