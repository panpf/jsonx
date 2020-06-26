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

package me.panpf.jsonx;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jsonx {

    private Jsonx() {
    }


    /* ************************************* check ***************************************** */


    /**
     * If the given json string is empty, for empty example: ' ', 'null', '{}', '[]' returns true
     */
    public static boolean isEmpty(@Nullable String json) {
        if (json == null) return true;
        json = json.trim();
        return "".equals(json) || "null".equalsIgnoreCase(json) || "{}".equalsIgnoreCase(json) || "[]".equals(json);
    }

    /**
     * If the given json string is not empty, for empty example: ' ', 'null', '{}', '[]' returns true
     */
    public static boolean isNotEmpty(@Nullable String json) {
        return !isEmpty(json);
    }

    /**
     * If the given json string is empty, for empty example: ' ', 'null', '{}' returns true
     */
    public static boolean isEmptyObject(@Nullable String json) {
        if (json == null) return true;
        json = json.trim();
        return "".equals(json) || "null".equalsIgnoreCase(json) || "{}".equalsIgnoreCase(json);
    }

    /**
     * If the given json string is not empty, for empty example: ' ', 'null', '{}' returns true
     */
    public static boolean isNotEmptyObject(@Nullable String json) {
        return !isEmptyObject(json);
    }

    /**
     * If the given json string is empty, for empty example: ' ', 'null', '[]' returns true
     */
    public static boolean isEmptyArray(@Nullable String json) {
        if (json == null) return true;
        json = json.trim();
        return "".equals(json) || "null".equalsIgnoreCase(json) || "[]".equals(json);
    }

    /**
     * If the given json string is not empty, for empty example: ' ', 'null', '[]' returns true
     */
    public static boolean isNotEmptyArray(@Nullable String json) {
        return !isEmptyArray(json);
    }

    /**
     * Return true if the given json string is a json object
     */
    public static boolean isObject(@Nullable String json) {
        if (json == null) return false;
        json = json.trim();
        return json.startsWith("{") && json.endsWith("}");
    }

    /**
     * Return true if the given json string is not a json object
     */
    public static boolean isNotObject(@Nullable String json) {
        return !isObject(json);
    }

    /**
     * Return true if the given json string is a json array
     */
    public static boolean isArray(@Nullable String json) {
        if (json == null) return false;
        json = json.trim();
        return json.startsWith("[") && json.endsWith("]");
    }

    /**
     * Return true if the given json string is not a json array
     */
    public static boolean isNotArray(@Nullable String json) {
        return !isArray(json);
    }


    /* ************************************* toJsonObject ***************************************** */


    /**
     * Convert a string of to a JSONObject
     */
    @NotNull
    public static JSONObject toJsonObject(@Nullable String json) throws JSONException {
        return new JSONObject(json != null ? json : "");
    }

    /**
     * Convert a object of to a JSONObject
     */
    @Nullable
    public static <T> JSONObject toJsonObject(@NotNull T item, @NotNull ToJsonObject<T> toJsonObject) throws JSONException {
        return toJsonObject.toJsonObject(item);
    }


    /* ************************************* toJsonArray ***************************************** */


    /**
     * Convert a string of to a JSONArray
     */
    @NotNull
    public static JSONArray toJsonArray(@Nullable String json) throws JSONException {
        return new JSONArray(json != null ? json : "");
    }

    /**
     * Convert a list of to a JSONArray
     */
    @Nullable
    public static <T> JSONArray toJsonArray(@Nullable List<T> list, @NotNull ToJsonObject<T> toJsonObject) throws JSONException {
        if (list == null || list.isEmpty()) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : list) {
            JSONObject jsonObject = toJsonObject.toJsonObject(item);
            if (jsonObject != null) {
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }

    /**
     * Convert a list of to a JSONArray
     */
    @Nullable
    public static <T> JSONArray toJsonArray(@Nullable List<T> list) {
        if (list == null || list.isEmpty()) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : list) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of to a JSONArray
     */
    @Nullable
    public static <T> JSONArray toJsonArray(@Nullable T[] array, @NotNull ToJsonObject<T> toJsonObject) throws JSONException {
        if (array == null || array.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : array) {
            JSONObject jsonObject = toJsonObject.toJsonObject(item);
            if (jsonObject != null) {
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }

    /**
     * Convert a array of to a JSONArray
     */
    @Nullable
    public static <T> JSONArray toJsonArray(@Nullable T[] array) {
        if (array == null || array.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : array) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of int to a JSONArray
     */
    @Nullable
    public static JSONArray toJsonArray(@Nullable int[] ints) {
        if (ints == null || ints.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (int item : ints) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of double to a JSONArray
     */
    @Nullable
    public static JSONArray toJsonArray(@Nullable double[] doubles) throws JSONException {
        if (doubles == null || doubles.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (double item : doubles) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of long to a JSONArray
     */
    @Nullable
    public static JSONArray toJsonArray(@Nullable long[] longs) {
        if (longs == null || longs.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (long item : longs) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of boolean to a JSONArray
     */
    @Nullable
    public static JSONArray toJsonArray(@Nullable boolean[] booleans) {
        if (booleans == null || booleans.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (boolean item : booleans) {
            jsonArray.put(item);
        }
        return jsonArray;
    }


    /**
     * Convert a list of to a JSONArray string
     */
    @Nullable
    public static <T> String toJsonArrayString(@Nullable List<T> list, @NotNull ToJsonObject<T> toJsonObject) throws JSONException {
        JSONArray jsonArray = toJsonArray(list, toJsonObject);
        return jsonArray != null ? jsonArray.toString() : null;
    }

    /**
     * Convert a array of to a JSONArray string
     */
    @Nullable
    public static <T> String toJsonArrayString(@Nullable T[] list, @NotNull ToJsonObject<T> toJsonObject) throws JSONException {
        JSONArray jsonArray = toJsonArray(list, toJsonObject);
        return jsonArray != null ? jsonArray.toString() : null;
    }

    /**
     * Convert a list of to a JSONArray string
     */
    @Nullable
    public static <T> String toJsonArrayString(@Nullable List<T> list) {
        JSONArray jsonArray = toJsonArray(list);
        return jsonArray != null ? jsonArray.toString() : null;
    }

    /**
     * Convert a array of to a JSONArray string
     */
    @Nullable
    public static <T> String toJsonArrayString(@Nullable T[] array) {
        JSONArray jsonArray = toJsonArray(array);
        return jsonArray != null ? jsonArray.toString() : null;
    }

    /**
     * Convert a array of int to a JSONArray string
     */
    @Nullable
    public static String toJsonArrayString(@Nullable int[] ints) {
        JSONArray jsonArray = toJsonArray(ints);
        return jsonArray != null ? jsonArray.toString() : null;
    }

    /**
     * Convert a array of double to a JSONArray string
     */
    @Nullable
    public static String toJsonArrayString(@Nullable double[] doubles) throws JSONException {
        JSONArray jsonArray = toJsonArray(doubles);
        return jsonArray != null ? jsonArray.toString() : null;
    }

    /**
     * Convert a array of long to a JSONArray string
     */
    @Nullable
    public static String toJsonArrayString(@Nullable long[] longs) {
        JSONArray jsonArray = toJsonArray(longs);
        return jsonArray != null ? jsonArray.toString() : null;
    }

    /**
     * Convert a array of boolean to a JSONArray string
     */
    @Nullable
    public static String toJsonArrayString(@Nullable boolean[] booleans) {
        JSONArray jsonArray = toJsonArray(booleans);
        return jsonArray != null ? jsonArray.toString() : null;
    }


    /* ************************************* toBean ***************************************** */


    /**
     * Convert JSONArray to the Bean
     */
    @Nullable
    public static <Bean> Bean toBean(@Nullable JSONObject jsonObject, @NotNull ToBean<Bean> toBean) throws JSONException {
        return jsonObject != null ? toBean.toBean(jsonObject) : null;
    }

    /**
     * Convert json to the Bean
     */
    @Nullable
    public static <Bean> Bean toBean(@Nullable String jsonObjectString, @NotNull ToBean<Bean> toBean) throws JSONException {
        return isNotEmptyObject(jsonObjectString) ? toBean.toBean(new JSONObject(jsonObjectString)) : null;
    }


    /**
     * Convert JSONArray to the Bean list
     */
    @Nullable
    public static <Bean> ArrayList<Bean> toBeanList(@Nullable JSONArray jsonArray, @NotNull ToBean<Bean> toBean) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        ArrayList<Bean> resultList = null;
        JSONObject jsonObject;
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            jsonObject = jsonArray.optJSONObject(i);
            if (jsonObject != null) {
                Bean bean = toBean.toBean(jsonObject);
                if (bean != null) {
                    if (resultList == null) {
                        resultList = new ArrayList<>(jsonArray.length());
                    }
                    resultList.add(bean);
                }
            }
        }
        return resultList;
    }

    /**
     * Convert json to the Bean list
     */
    @Nullable
    public static <Bean> ArrayList<Bean> toBeanList(@Nullable String jsonArrayString, @NotNull ToBean<Bean> toBean) throws JSONException {
        return isNotEmptyArray(jsonArrayString) ? toBeanList(new JSONArray(jsonArrayString), toBean) : null;
    }


    /**
     * Convert a JSONArray to a String list
     */
    @Nullable
    public static List<String> toStringList(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() <= 0) return null;
        ArrayList<String> dataList = new ArrayList<>(jsonArray.length());
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataList.add(jsonArray.getString(i));
        }
        return dataList;
    }

    /**
     * Convert a json to a String list
     */
    @Nullable
    public static List<String> toStringList(@Nullable String jsonArrayString) throws JSONException {
        return isNotEmptyArray(jsonArrayString) ? toStringList(new JSONArray(jsonArrayString)) : null;
    }


    /**
     * Convert a JSONArray to a String array
     */
    @Nullable
    public static String[] toStringArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        String[] dataStrings = new String[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataStrings[i] = jsonArray.getString(i);
        }
        return dataStrings;
    }

    /**
     * Convert a json to a String array
     */
    @Nullable
    public static String[] toStringArray(@Nullable String jsonArrayString) throws JSONException {
        return isNotEmptyArray(jsonArrayString) ? toStringArray(new JSONArray(jsonArrayString)) : null;
    }


    /**
     * Convert a JSONArray to a int array
     */
    @Nullable
    public static int[] toIntArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        int[] dataInts = new int[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataInts[i] = jsonArray.getInt(i);
        }
        return dataInts;
    }

    /**
     * Convert a json to a int array
     */
    @Nullable
    public static int[] toIntArray(@Nullable String jsonArrayString) throws JSONException {
        return isNotEmptyArray(jsonArrayString) ? toIntArray(new JSONArray(jsonArrayString)) : null;
    }


    /**
     * Convert a JSONArray to a double array
     */
    @Nullable
    public static double[] toDoubleArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        double[] dataInts = new double[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataInts[i] = jsonArray.getDouble(i);
        }
        return dataInts;
    }

    /**
     * Convert a json to a double array
     */
    @Nullable
    public static double[] toDoubleArray(@Nullable String jsonArrayString) throws JSONException {
        return isNotEmptyArray(jsonArrayString) ? toDoubleArray(new JSONArray(jsonArrayString)) : null;
    }


    /**
     * Convert a JSONArray to a long array
     */
    @Nullable
    public static long[] toLongArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        long[] dataInts = new long[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataInts[i] = jsonArray.getLong(i);
        }
        return dataInts;
    }

    /**
     * Convert a json to a long array
     */
    @Nullable
    public static long[] toLongArray(@Nullable String jsonArrayString) throws JSONException {
        return isNotEmptyArray(jsonArrayString) ? toLongArray(new JSONArray(jsonArrayString)) : null;
    }


    /**
     * Convert a JSONArray to a boolean array
     */
    @Nullable
    public static boolean[] toBooleanArray(@Nullable JSONArray jsonArray) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        boolean[] dataInts = new boolean[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataInts[i] = jsonArray.getBoolean(i);
        }
        return dataInts;
    }

    /**
     * Convert a json to a boolean array
     */
    @Nullable
    public static boolean[] toBooleanArray(@Nullable String jsonArrayString) throws JSONException {
        return isNotEmptyArray(jsonArrayString) ? toBooleanArray(new JSONArray(jsonArrayString)) : null;
    }


    /* ************************************* opt and get ***************************************** */


    /**
     * Returns the value mapped by keys
     */
    @Nullable
    public static Object opt(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        return null;
    }


    /**
     * Returns the value mapped by keys and turn it into a string, or defaultValue if no such mapping exists.
     */
    @NotNull
    public static String optString(@Nullable JSONObject jsonObject, @NotNull String[] keys, @NotNull String defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    return value.toString();
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys and turn it into a string, or empty string if no such mapping exists.
     */
    @NotNull
    public static String optString(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optString(jsonObject, keys, "");
    }

    /**
     * Returns the value mapped by keys and turn it into a int, or defaultValue if no such mapping exists.
     */
    public static int optInt(@Nullable JSONObject jsonObject, @NotNull String[] keys, int defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Integer integerValue = toInteger(value);
                    if (integerValue != null) return integerValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys and turn it into a int, or 0 if no such mapping exists.
     */
    public static int optInt(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optInt(jsonObject, keys, 0);
    }

    /**
     * Returns the value mapped by keys and turn it into a long, or defaultValue if no such mapping exists.
     */
    public static long optLong(@Nullable JSONObject jsonObject, @NotNull String[] keys, long defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Long longValue = toLong(value);
                    if (longValue != null) return longValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys and turn it into a long, or 0L if no such mapping exists.
     */
    public static long optLong(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optLong(jsonObject, keys, 0L);
    }

    /**
     * Returns the value mapped by keys and turn it into a boolean, or defaultValue if no such mapping exists.
     */
    public static boolean optBoolean(@Nullable JSONObject jsonObject, @NotNull String[] keys, boolean defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Boolean booleanValue = toBoolean(value);
                    if (booleanValue != null) return booleanValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys and turn it into a boolean, or false if no such mapping exists.
     */
    public static boolean optBoolean(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optBoolean(jsonObject, keys, false);
    }

    /**
     * Returns the value mapped by keys and turn it into a double, or defaultValue if no such mapping exists.
     */
    public static double optDouble(@Nullable JSONObject jsonObject, @NotNull String[] keys, double defaultValue) {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Double doubleValue = toDouble(value);
                    if (doubleValue != null) return doubleValue;
                }
            }
        }

        return defaultValue;
    }

    /**
     * Returns the value mapped by keys and turn it into a double, or 0.0 if no such mapping exists.
     */
    public static double optDouble(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optDouble(jsonObject, keys, 0.0);
    }

    /**
     * Returns the value mapped by keys, or null if no such mapping exists.
     */
    @Nullable
    public static JSONObject optJSONObject(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        if (jsonObject != null) {
            JSONObject value;
            for (String key : keys) {
                value = jsonObject.optJSONObject(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        return null;
    }

    /**
     * Returns the value mapped by keys, or null if no such mapping exists.
     */
    @Nullable
    public static JSONArray optJSONArray(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        if (jsonObject != null) {
            JSONArray value;
            for (String key : keys) {
                value = jsonObject.optJSONArray(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        return null;
    }


    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    @NotNull
    public static Object get(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }


    /**
     * Returns the value mapped by keys if it exists and turn it into a string, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    @NotNull
    public static String getString(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    String stringValue = toString(value);
                    if (stringValue != null) return stringValue;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a int, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static int getInt(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Integer integerValue = toInteger(value);
                    if (integerValue != null) return integerValue;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a long, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static long getLong(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Long longValue = toLong(value);
                    if (longValue != null) return longValue;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a boolean, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static boolean getBoolean(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Boolean booleanValue = toBoolean(value);
                    if (booleanValue != null) return booleanValue;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a double, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static double getDouble(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            Object value;
            for (String key : keys) {
                value = jsonObject.opt(key);
                if (value != null && value != JSONObject.NULL) {
                    Double doubleValue = toDouble(value);
                    if (doubleValue != null) return doubleValue;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    @NotNull
    public static JSONArray getJSONArray(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            JSONArray value;
            for (String key : keys) {
                value = jsonObject.optJSONArray(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    @NotNull
    public static JSONObject getJSONObject(@Nullable JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        if (jsonObject != null) {
            JSONObject value;
            for (String key : keys) {
                value = jsonObject.optJSONObject(key);
                if (value != null && value != JSONObject.NULL) {
                    return value;
                }
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }


    /* ************************************* format ***************************************** */


    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public static String format(@Nullable JSONObject jsonObject) {
        return new JsonFormatter().format(jsonObject);
    }

    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public static String format(@Nullable JSONArray jsonArray) {
        return new JsonFormatter().format(jsonArray);
    }

    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public static String format(@Nullable String json) throws JSONException {
        return new JsonFormatter().format(json);
    }


    /* ************************************* Private ***************************************** */


    /**
     * Object to Integer
     */
    @Nullable
    private static Integer toInteger(@Nullable Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return (int) Double.parseDouble((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    /**
     * Object to Long
     */
    @Nullable
    private static Long toLong(@Nullable Object value) {
        if (value instanceof Long) {
            return (Long) value;
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            try {
                return (long) Double.parseDouble((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    /**
     * Object to Boolean
     */
    @Nullable
    private static Boolean toBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof String) {
            String stringValue = (String) value;
            if ("true".equalsIgnoreCase(stringValue)) {
                return true;
            } else if ("false".equalsIgnoreCase(stringValue)) {
                return false;
            }
        }
        return null;
    }

    /**
     * Object to Double
     */
    @Nullable
    private static Double toDouble(Object value) {
        if (value instanceof Double) {
            return (Double) value;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            try {
                return Double.valueOf((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return null;
    }

    /**
     * Object to String
     */
    @Nullable
    private static String toString(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else if (value != null) {
            return String.valueOf(value);
        }
        return null;
    }
}