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

package com.github.panpf.jsonx

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/* ************************************* check ***************************************** */


/**
 * Return true if the given json string is a json object or json array
 */
inline fun String?.isJSON(): Boolean = Jsonx.isJSON(this)

/**
 * Return true if the given json string is not a json object or json array
 */
inline fun String?.isNotJSON(): Boolean = Jsonx.isNotJSON(this)

/**
 * Return true if the given json string is a json object
 */
inline fun String?.isJSONObject(): Boolean = Jsonx.isJSONObject(this)

/**
 * Return true if the given json string is not a json object
 */
inline fun String?.isNotJSONObject(): Boolean = Jsonx.isNotJSONObject(this)

/**
 * Return true if the given json string is a json array
 */
inline fun String?.isJSONArray(): Boolean = Jsonx.isJSONArray(this)

/**
 * Return true if the given json string is not a json array
 */
inline fun String?.isNotJSONArray(): Boolean = Jsonx.isNotJSONArray(this)


/**
 * If the given json string is empty, for empty example: ' ', 'null', '{}', '[]' returns true
 */
inline fun String?.isEmptyJSON(): Boolean = Jsonx.isEmptyJSON(this)

/**
 * If the given json string is not empty, for empty example: ' ', 'null', '{}', '[]' returns true
 */
inline fun String?.isNotEmptyJSON(): Boolean = Jsonx.isNotEmptyJSON(this)

/**
 * If the given json string is empty, for empty example: ' ', 'null', '{}' returns true
 */
inline fun String?.isEmptyJSONObject(): Boolean = Jsonx.isEmptyJSONObject(this)

/**
 * If the given json string is not empty, for empty example: ' ', 'null', '{}' returns true
 */
inline fun String?.isNotEmptyJSONObject(): Boolean = Jsonx.isNotEmptyJSONObject(this)

/**
 * If the given json string is empty, for empty example: ' ', 'null', '[]' returns true
 */
inline fun String?.isEmptyJSONArray(): Boolean = Jsonx.isEmptyJSONArray(this)

/**
 * If the given json string is not empty, for empty example: ' ', 'null', '[]' returns true
 */
inline fun String?.isNotEmptyJSONArray(): Boolean = Jsonx.isNotEmptyJSONArray(this)


/* ************************************* toJsonObject ***************************************** */


/**
 * Convert a string of to a JSONObject
 */
@Throws(JSONException::class)
inline fun String.toJSONObject(): JSONObject = Jsonx.toJSONObject(this)

/**
 * Convert a string of to a JSONObject. If the json string is null or empty or not a valid json object, return null
 */
inline fun String?.toJSONObjectOrNull(): JSONObject? = Jsonx.toJSONObjectOrNull(this)

/**
 * Convert a object of to a JSONObject
 */
@Throws(JSONException::class)
inline fun <T> T.toJSONObject(toJSONObject: ToJSONObject<T>): JSONObject = Jsonx.toJSONObject(this, toJSONObject)

/**
 * Convert a object of to a JSONObject. If item is null or toJsonObjectOrNull returns null, then finally return null
 */
@Throws(JSONException::class)
inline fun <T> T?.toJSONObjectOrNull(toJSONObjectOrNull: ToJSONObjectOrNull<T>): JSONObject? = Jsonx.toJSONObjectOrNull(this, toJSONObjectOrNull)


/* ************************************* toJsonArray ***************************************** */

/**
 * Convert a string of to a JSONArray
 */
@Throws(JSONException::class)
inline fun String.toJSONArray(): JSONArray = Jsonx.toJSONArray(this)

/**
 * Convert a string of to a JSONArray. If the json string is null or empty or not a valid json array, return null
 */
inline fun String?.toJSONArrayOrNull(): JSONArray? = Jsonx.toJSONArrayOrNull(this)

/**
 * Convert a list of to a JSONArray
 */
@Throws(JSONException::class)
inline fun <T> List<T>.toJSONArray(toJSONObject: ToJSONObject<T>): JSONArray = Jsonx.toJSONArray(this, toJSONObject)

/**
 * Convert a list of to a JSONArray. If list is null or empty, or toJsonObjectOrNull all returns null, then finally return null
 */
@Throws(JSONException::class)
inline fun <T> List<T>?.toJSONArrayOrNull(toJSONObjectOrNull: ToJSONObjectOrNull<T>): JSONArray? = Jsonx.toJSONArrayOrNull(this, toJSONObjectOrNull)

/**
 * Convert a list of to a JSONArray
 */
inline fun <T> List<T>.toJSONArray(): JSONArray = Jsonx.toJSONArray(this)

/**
 * Convert a list of to a JSONArray. If list is null or empty, or list all item are null, then finally return null
 */
inline fun <T> List<T>?.toJSONArrayOrNull(): JSONArray? = Jsonx.toJSONArrayOrNull(this)

/**
 * Convert a array of to a JSONArray
 */
@Throws(JSONException::class)
inline fun <T> Array<T>.toJSONArray(toJSONObject: ToJSONObject<T>): JSONArray = Jsonx.toJSONArray(this, toJSONObject)

/**
 * Convert a array of to a JSONArray. If array is null or empty, or toJsonObjectOrNull all returns null, then finally return null
 */
@Throws(JSONException::class)
inline fun <T> Array<T>?.toJSONArrayOrNull(toJSONObjectOrNull: ToJSONObjectOrNull<T>): JSONArray? = Jsonx.toJSONArrayOrNull(this, toJSONObjectOrNull)

/**
 * Convert a array of to a JSONArray
 */
inline fun <T> Array<T>.toJSONArray(): JSONArray = Jsonx.toJSONArray(this)

/**
 * Convert a array of to a JSONArray. If array is null or empty, or array item all is null, then finally return null
 */
inline fun <T> Array<T>?.toJSONArrayOrNull(): JSONArray? = Jsonx.toJSONArrayOrNull(this)

/**
 * Convert a array of int to a JSONArray
 */
inline fun IntArray.toJSONArray(): JSONArray = Jsonx.toJSONArray(this)

/**
 * Convert a array of int to a JSONArray. If array is null or empty, then finally return null
 */
inline fun IntArray?.toJSONArrayOrNull(): JSONArray? = Jsonx.toJSONArrayOrNull(this)

/**
 * Convert a array of double to a JSONArray
 */
@Throws(JSONException::class)
inline fun DoubleArray.toJSONArray(): JSONArray = Jsonx.toJSONArray(this)

/**
 * Convert a array of double to a JSONArray. If array is null or empty, then finally return null
 */
@Throws(JSONException::class)
inline fun DoubleArray?.toJSONArrayOrNull(): JSONArray? = Jsonx.toJSONArrayOrNull(this)

/**
 * Convert a array of long to a JSONArray
 */
inline fun LongArray.toJSONArray(): JSONArray = Jsonx.toJSONArray(this)

/**
 * Convert a array of long to a JSONArray. If array is null or empty, then finally return null
 */
inline fun LongArray?.toJSONArrayOrNull(): JSONArray? = Jsonx.toJSONArrayOrNull(this)

/**
 * Convert a array of boolean to a JSONArray
 */
inline fun BooleanArray.toJSONArray(): JSONArray = Jsonx.toJSONArray(this)

/**
 * Convert a array of boolean to a JSONArray. If array is null or empty, then finally return null
 */
inline fun BooleanArray?.toJSONArrayOrNull(): JSONArray? = Jsonx.toJSONArrayOrNull(this)


/* ************************************* toBean ***************************************** */


/**
 * Convert JSONArray to the Bean
 */
@Throws(JSONException::class)
inline fun <Bean> JSONObject.toBean(toBean: ToBean<Bean>): Bean = Jsonx.toBean(this, toBean)

/**
 * Convert JSONArray to the Bean. If jsonObject is null or toBeanOrNull return null, then finally return null
 */
@Throws(JSONException::class)
inline fun <Bean> JSONObject?.toBeanOrNull(toBeanOrNull: ToBeanOrNull<Bean>): Bean? = Jsonx.toBeanOrNull(this, toBeanOrNull)


/**
 * Convert JSONArray to the Bean list
 */
@Throws(JSONException::class)
inline fun <Bean> JSONArray.toBeanList(toBean: ToBean<Bean>): ArrayList<Bean> = Jsonx.toBeanList(this, toBean)

/**
 * Convert JSONArray to the Bean list. If jsonArray is null or empty, or toBeanOrNull all return null, then finally return null
 */
@Throws(JSONException::class)
inline fun <Bean> JSONArray?.toBeanListOrNull(toBeanOrNull: ToBeanOrNull<Bean>): ArrayList<Bean>? = Jsonx.toBeanListOrNull(this, toBeanOrNull)


/**
 * Convert a JSONArray to a String array
 */
@Throws(JSONException::class)
inline fun JSONArray.toStringArray(): Array<String> = Jsonx.toStringArray(this)

/**
 * Convert a JSONArray to a String array. If jsonArray is null or empty, or all item are not string, then finally return null
 */
inline fun JSONArray?.toStringArrayOrNull(): Array<String>? = Jsonx.toStringArrayOrNull(this)

/**
 * Convert a JSONArray to a int array
 */
@Throws(JSONException::class)
inline fun JSONArray.toIntArray(): IntArray = Jsonx.toIntArray(this)

/**
 * Convert a JSONArray to a int array. If jsonArray is null or empty, or all item are not int, then finally return null
 */
inline fun JSONArray?.toIntArrayOrNull(): IntArray? = Jsonx.toIntArrayOrNull(this)

/**
 * Convert a JSONArray to a double array
 */
@Throws(JSONException::class)
inline fun JSONArray.toDoubleArray(): DoubleArray = Jsonx.toDoubleArray(this)

/**
 * Convert a JSONArray to a double array. If jsonArray is null or empty, or all item are not double, then finally return null
 */
inline fun JSONArray?.toDoubleArrayOrNull(): DoubleArray? = Jsonx.toDoubleArrayOrNull(this)

/**
 * Convert a JSONArray to a long array
 */
@Throws(JSONException::class)
inline fun JSONArray.toLongArray(): LongArray = Jsonx.toLongArray(this)

/**
 * Convert a JSONArray to a long array. If jsonArray is null or empty, or all item are not long, then finally return null
 */
inline fun JSONArray?.toLongArrayOrNull(): LongArray? = Jsonx.toLongArrayOrNull(this)

/**
 * Convert a JSONArray to a boolean array
 */
@Throws(JSONException::class)
inline fun JSONArray.toBooleanArray(): BooleanArray = Jsonx.toBooleanArray(this)

/**
 * Convert a JSONArray to a boolean array. If jsonArray is null or empty, or all item are not boolean, then finally return null
 */
inline fun JSONArray?.toBooleanArrayOrNull(): BooleanArray? = Jsonx.toBooleanArrayOrNull(this)


/* ************************************* opt and get ***************************************** */


/**
 * Returns the value mapped by keys
 */
inline fun JSONObject?.optWithKeys(keys: Array<String>): Any? = Jsonx.optWithKeys(this, keys)


/**
 * Returns the value mapped by keys and turn it into a string, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optStringWithKeys(keys: Array<String>, defaultValue: String = ""): String = Jsonx.optStringWithKeys(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a int, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optIntWithKeys(keys: Array<String>, defaultValue: Int = 0): Int = Jsonx.optIntWithKeys(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a long, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optLongWithKeys(keys: Array<String>, defaultValue: Long = 0L): Long = Jsonx.optLongWithKeys(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a boolean, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optBooleanWithKeys(keys: Array<String>, defaultValue: Boolean = false): Boolean = Jsonx.optBooleanWithKeys(this, keys, defaultValue)

/**
 * Returns the value mapped by keys and turn it into a double, or defaultValue if no such mapping exists.
 */
inline fun JSONObject?.optDoubleWithKeys(keys: Array<String>, defaultValue: Double = 0.0): Double = Jsonx.optDoubleWithKeys(this, keys, defaultValue)

/**
 * Returns the value mapped by keys, or null if no such mapping exists.
 */
inline fun JSONObject?.optJSONObjectWithKeys(keys: Array<String>): JSONObject? = Jsonx.optJSONObjectWithKeys(this, keys)

/**
 * Returns the value mapped by keys, or null if no such mapping exists.
 */
inline fun JSONObject?.optJSONArrayWithKeys(keys: Array<String>): JSONArray? = Jsonx.optJSONArrayWithKeys(this, keys)


/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getWithKeys(keys: Array<String>): Any = Jsonx.getWithKeys(this, keys)


/**
 * Returns the value mapped by keys if it exists and turn it into a string, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getStringWithKeys(keys: Array<String>): String = Jsonx.getStringWithKeys(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a int, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getIntWithKeys(keys: Array<String>): Int = Jsonx.getIntWithKeys(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a long, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getLongWithKeys(keys: Array<String>): Long = Jsonx.getLongWithKeys(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a boolean, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getBooleanWithKeys(keys: Array<String>): Boolean = Jsonx.getBooleanWithKeys(this, keys)

/**
 * Returns the value mapped by keys if it exists and turn it into a double, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getDoubleWithKeys(keys: Array<String>): Double = Jsonx.getDoubleWithKeys(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getJSONArrayWithKeys(keys: Array<String>): JSONArray = Jsonx.getJSONArrayWithKeys(this, keys)

/**
 * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
 *
 * @throws JSONException if no such mapping exists.
 */
@Throws(JSONException::class)
inline fun JSONObject.getJSONObjectWithKeys(keys: Array<String>): JSONObject = Jsonx.getJSONObjectWithKeys(this, keys)


/* ************************************* format ***************************************** */


/**
 * Formatted output for easy viewing
 */
inline fun JSONObject.formatToString(): String = Jsonx.formatToString(this)

/**
 * Formatted output for easy viewing. If jsonObject is null or empty, then return null
 */
inline fun JSONObject?.formatToStringOrNull(): String? = Jsonx.formatToStringOrNull(this)

/**
 * Formatted output for easy viewing
 */
inline fun JSONArray.formatToString(): String = Jsonx.formatToString(this)

/**
 * Formatted output for easy viewing. If jsonArray is null or empty, then return null
 */
inline fun JSONArray?.formatToStringOrNull(): String? = Jsonx.formatToStringOrNull(this)

/**
 * Formatted output for easy viewing
 */
@Throws(JSONException::class)
inline fun String.formatJSON(): String = Jsonx.formatJSON(this)

/**
 * Formatted output for easy viewing. If the json string is empty, for example: ' ', 'null', '{}', '[]', then returns null
 */
@Throws(JSONException::class)
inline fun String?.formatJSONOrNull(): String? = Jsonx.formatJSONOrNull(this)