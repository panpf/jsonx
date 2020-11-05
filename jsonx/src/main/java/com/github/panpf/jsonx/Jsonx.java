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

package com.github.panpf.jsonx;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Jsonx {

    private Jsonx() {
    }


    /* ************************************* check ***************************************** */


    /**
     * Return true if the given json string is a json object or json array
     */
    public static boolean isJSON(@Nullable String json) {
        if (json == null || json.length() == 0) return false;
        int startIndex = 0;
        int endIndex = json.length();
        while ((startIndex < endIndex) && (json.charAt(startIndex) <= ' ')) {
            startIndex++;
        }
        while ((startIndex < endIndex) && (json.charAt(endIndex - 1) <= ' ')) {
            endIndex--;
        }
        if (startIndex >= endIndex) return false;    // json string are all composed of blank characters
        return ('{' == json.charAt(startIndex) && '}' == json.charAt(endIndex - 1)) || ('[' == json.charAt(startIndex) && ']' == json.charAt(endIndex - 1));
    }

    /**
     * Return true if the given json string is not a json object json array
     */
    public static boolean isNotJSON(@Nullable String json) {
        return !isJSON(json);
    }

    /**
     * Return true if the given json string is a json object
     */
    public static boolean isJSONObject(@Nullable String json) {
        if (json == null || json.length() == 0) return false;
        int startIndex = 0;
        int endIndex = json.length();
        while ((startIndex < endIndex) && (json.charAt(startIndex) <= ' ')) {
            startIndex++;
        }
        while ((startIndex < endIndex) && (json.charAt(endIndex - 1) <= ' ')) {
            endIndex--;
        }
        if (startIndex >= endIndex) return false;    // json string are all composed of blank characters
        return '{' == json.charAt(startIndex) && '}' == json.charAt(endIndex - 1);
    }

    /**
     * Return true if the given json string is not a json object
     */
    public static boolean isNotJSONObject(@Nullable String json) {
        return !isJSONObject(json);
    }

    /**
     * Return true if the given json string is a json array
     */
    public static boolean isJSONArray(@Nullable String json) {
        if (json == null || json.length() == 0) return false;
        int startIndex = 0;
        int endIndex = json.length();
        while ((startIndex < endIndex) && (json.charAt(startIndex) <= ' ')) {
            startIndex++;
        }
        while ((startIndex < endIndex) && (json.charAt(endIndex - 1) <= ' ')) {
            endIndex--;
        }
        if (startIndex >= endIndex) return false;    // json string are all composed of blank characters
        return '[' == json.charAt(startIndex) && ']' == json.charAt(endIndex - 1);
    }

    /**
     * Return true if the given json string is not a json array
     */
    public static boolean isNotJSONArray(@Nullable String json) {
        return !isJSONArray(json);
    }


    /**
     * If the given json string is empty, for empty example: '{}', '[]' returns true
     */
    public static boolean isEmptyJSON(@Nullable String json) {
        if (json == null || json.length() == 0) return false;
        int startIndex = 0;
        int endIndex = json.length();
        while ((startIndex < endIndex) && (json.charAt(startIndex) <= ' ')) {
            startIndex++;
        }
        while ((startIndex < endIndex) && (json.charAt(endIndex - 1) <= ' ')) {
            endIndex--;
        }
        if (startIndex >= endIndex) return false;    // json string are all composed of blank characters
        if (('{' == json.charAt(startIndex) && '}' == json.charAt(endIndex - 1)) || ('[' == json.charAt(startIndex) && ']' == json.charAt(endIndex - 1))) {
            for (int index = startIndex + 1, end = endIndex - 1; index < end; index++) {
                if (json.charAt(index) > ' ') {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * If the given json string is not empty, for empty example: '{}', '[]' returns true
     */
    public static boolean isNotEmptyJSON(@Nullable String json) {
        return !isEmptyJSON(json);
    }

    /**
     * If the given json string is empty, for empty example: '{}' returns true
     */
    public static boolean isEmptyJSONObject(@Nullable String json) {
        if (json == null || json.length() == 0) return false;
        int startIndex = 0;
        int endIndex = json.length();
        while ((startIndex < endIndex) && (json.charAt(startIndex) <= ' ')) {
            startIndex++;
        }
        while ((startIndex < endIndex) && (json.charAt(endIndex - 1) <= ' ')) {
            endIndex--;
        }
        if (startIndex >= endIndex) return false;    // json string are all composed of blank characters
        if ('{' == json.charAt(startIndex) && '}' == json.charAt(endIndex - 1)) {
            for (int index = startIndex + 1, end = endIndex - 1; index < end; index++) {
                if (json.charAt(index) > ' ') {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * If the given json string is not empty, for empty example: '{}' returns true
     */
    public static boolean isNotEmptyJSONObject(@Nullable String json) {
        return !isEmptyJSONObject(json);
    }

    /**
     * If the given json string is empty, for empty example: '[]' returns true
     */
    public static boolean isEmptyJSONArray(@Nullable String json) {
        if (json == null || json.length() == 0) return false;
        int startIndex = 0;
        int endIndex = json.length();
        while ((startIndex < endIndex) && (json.charAt(startIndex) <= ' ')) {
            startIndex++;
        }
        while ((startIndex < endIndex) && (json.charAt(endIndex - 1) <= ' ')) {
            endIndex--;
        }
        if (startIndex >= endIndex) return false;    // json string are all composed of blank characters
        if ('[' == json.charAt(startIndex) && ']' == json.charAt(endIndex - 1)) {
            for (int index = startIndex + 1, end = endIndex - 1; index < end; index++) {
                if (json.charAt(index) > ' ') {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * If the given json string is not empty, for empty example: '[]' returns true
     */
    public static boolean isNotEmptyJSONArray(@Nullable String json) {
        return !isEmptyJSONArray(json);
    }


    /* ************************************* toJsonObject ***************************************** */


    /**
     * Convert a string of to a JSONObject
     */
    @NotNull
    public static JSONObject toJSONObject(@NotNull String json) throws JSONException {
        return new JSONObject(json);
    }

    /**
     * Convert a string of to a JSONObject. If the json string is null or empty or not a valid json object, return null
     */
    @Nullable
    public static JSONObject toJSONObjectOrNull(@Nullable String json) {
        try {
            return isJSONObject(json) && isNotEmptyJSONObject(json) ? new JSONObject(json) : null;
        } catch (JSONException e) {
            e.printStackTrace();
            new JSONException("error='" + e.getMessage() + "' , sourceJson='" + json + "'").printStackTrace();
            return null;
        }
    }

    /**
     * Convert a object of to a JSONObject
     */
    @NotNull
    public static <T> JSONObject toJSONObject(@NotNull T item, @NotNull ToJSONObject<T> toJSONObject) throws JSONException {
        return toJSONObject.toJSONObject(item);
    }

    /**
     * Convert a object of to a JSONObject. If item is null or toJSONObjectOrNull returns null, then finally return null
     */
    @Nullable
    public static <T> JSONObject toJSONObjectOrNull(@Nullable T item, @NotNull ToJSONObjectOrNull<T> toJSONObjectOrNull) throws JSONException {
        return item != null ? toJSONObjectOrNull.toJSONObjectOrNull(item) : null;
    }


    /* ************************************* toJsonArray ***************************************** */


    /**
     * Convert a string of to a JSONArray
     */
    @NotNull
    public static JSONArray toJSONArray(@NotNull String json) throws JSONException {
        return new JSONArray(json);
    }

    /**
     * Convert a string of to a JSONArray. If the json string is null or empty or not a valid json array, return null
     */
    @Nullable
    public static JSONArray toJSONArrayOrNull(@Nullable String json) {
        try {
            return isJSONArray(json) && isNotEmptyJSONArray(json) ? new JSONArray(json) : null;
        } catch (JSONException e) {
            e.printStackTrace();
            new JSONException("error='" + e.getMessage() + "' , sourceJson='" + json + "'").printStackTrace();
            return null;
        }
    }

    /**
     * Convert a list of to a JSONArray
     */
    @NotNull
    public static <T> JSONArray toJSONArray(@NotNull List<T> list, @NotNull ToJSONObject<T> toJSONObject) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (T item : list) {
            jsonArray.put(toJSONObject.toJSONObject(item));
        }
        return jsonArray;
    }

    /**
     * Convert a list of to a JSONArray. If list is null or empty, or toJsonObjectOrNull all returns null, then finally return null
     */
    @Nullable
    public static <T> JSONArray toJSONArrayOrNull(@Nullable List<T> list, @NotNull ToJSONObjectOrNull<T> toJSONObjectOrNull) throws JSONException {
        if (list == null || list.isEmpty()) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : list) {
            JSONObject jsonObject = item != null ? toJSONObjectOrNull.toJSONObjectOrNull(item) : null;
            if (jsonObject != null) {
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray.length() > 0 ? jsonArray : null;
    }

    /**
     * Convert a list of to a JSONArray
     */
    @NotNull
    public static <T> JSONArray toJSONArray(@NotNull List<T> list) {
        JSONArray jsonArray = new JSONArray();
        for (T item : list) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a list of to a JSONArray. If list is null or empty, or list all item are null, then finally return null
     */
    @Nullable
    public static <T> JSONArray toJSONArrayOrNull(@Nullable List<T> list) {
        if (list == null || list.isEmpty()) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : list) {
            if (item != null) {
                jsonArray.put(item);
            }
        }
        return jsonArray.length() > 0 ? jsonArray : null;
    }

    /**
     * Convert a array of to a JSONArray
     */
    @NotNull
    public static <T> JSONArray toJSONArray(@NotNull T[] array, @NotNull ToJSONObject<T> toJsonObject) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (T item : array) {
            jsonArray.put(toJsonObject.toJSONObject(item));
        }
        return jsonArray;
    }

    /**
     * Convert a array of to a JSONArray. If array is null or empty, or toJsonObjectOrNull all returns null, then finally return null
     */
    @Nullable
    public static <T> JSONArray toJSONArrayOrNull(@Nullable T[] array, @NotNull ToJSONObjectOrNull<T> toJsonObjectOrNull) throws JSONException {
        if (array == null || array.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : array) {
            JSONObject jsonObject = item != null ? toJsonObjectOrNull.toJSONObjectOrNull(item) : null;
            if (jsonObject != null) {
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray.length() > 0 ? jsonArray : null;
    }

    /**
     * Convert a array of to a JSONArray
     */
    @NotNull
    public static <T> JSONArray toJSONArray(@NotNull T[] array) {
        JSONArray jsonArray = new JSONArray();
        for (T item : array) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of to a JSONArray. If array is null or empty, or array item all is null, then finally return null
     */
    @Nullable
    public static <T> JSONArray toJSONArrayOrNull(@Nullable T[] array) {
        if (array == null || array.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (T item : array) {
            if (item != null) {
                jsonArray.put(item);
            }
        }
        return jsonArray.length() > 0 ? jsonArray : null;
    }

    /**
     * Convert a array of int to a JSONArray
     */
    @NotNull
    public static JSONArray toJSONArray(@NotNull int[] ints) {
        JSONArray jsonArray = new JSONArray();
        for (int item : ints) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of int to a JSONArray. If array is null or empty, then finally return null
     */
    @Nullable
    public static JSONArray toJSONArrayOrNull(@Nullable int[] ints) {
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
    @NotNull
    public static JSONArray toJSONArray(@NotNull double[] doubles) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (double item : doubles) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of double to a JSONArray. If array is null or empty, then finally return null
     */
    @Nullable
    public static JSONArray toJSONArrayOrNull(@Nullable double[] doubles) throws JSONException {
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
    @NotNull
    public static JSONArray toJSONArray(@NotNull long[] longs) {
        JSONArray jsonArray = new JSONArray();
        for (long item : longs) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of long to a JSONArray. If array is null or empty, then finally return null
     */
    @Nullable
    public static JSONArray toJSONArrayOrNull(@Nullable long[] longs) {
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
    @NotNull
    public static JSONArray toJSONArray(@NotNull boolean[] booleans) {
        JSONArray jsonArray = new JSONArray();
        for (boolean item : booleans) {
            jsonArray.put(item);
        }
        return jsonArray;
    }

    /**
     * Convert a array of boolean to a JSONArray. If array is null or empty, then finally return null
     */
    @Nullable
    public static JSONArray toJSONArrayOrNull(@Nullable boolean[] booleans) {
        if (booleans == null || booleans.length == 0) return null;
        JSONArray jsonArray = new JSONArray();
        for (boolean item : booleans) {
            jsonArray.put(item);
        }
        return jsonArray;
    }


    /* ************************************* toBean ***************************************** */


    /**
     * Convert JSONArray to the Bean
     */
    @NotNull
    public static <Bean> Bean toBean(@NotNull JSONObject jsonObject, @NotNull ToBean<Bean> toBean) throws JSONException {
        return toBean.toBean(jsonObject);
    }

    /**
     * Convert JSONArray to the Bean. If jsonObject is null or toBeanOrNull return null, then finally return null
     */
    @Nullable
    public static <Bean> Bean toBeanOrNull(@Nullable JSONObject jsonObject, @NotNull ToBeanOrNull<Bean> toBeanOrNull) throws JSONException {
        return jsonObject != null ? toBeanOrNull.toBeanOrNull(jsonObject) : null;
    }


    /**
     * Convert JSONArray to the Bean list
     */
    @NotNull
    public static <Bean> ArrayList<Bean> toBeanList(@NotNull JSONArray jsonArray, @NotNull ToBean<Bean> toBean) throws JSONException {
        ArrayList<Bean> resultList = new ArrayList<>(jsonArray.length());
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            resultList.add(toBean.toBean(jsonArray.getJSONObject(i)));
        }
        return resultList;
    }

    /**
     * Convert JSONArray to the Bean list. If jsonArray is null or empty, or toBeanOrNull all return null, then finally return null
     */
    @Nullable
    public static <Bean> ArrayList<Bean> toBeanListOrNull(@Nullable JSONArray jsonArray, @NotNull ToBeanOrNull<Bean> toBeanOrNull) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        ArrayList<Bean> resultList = new ArrayList<>(jsonArray.length());
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            Bean bean = jsonObject != null ? toBeanOrNull.toBeanOrNull(jsonObject) : null;
            if (bean != null) {
                resultList.add(bean);
            }
        }
        return resultList.size() > 0 ? resultList : null;
    }

    /**
     * Convert JSONArray to the Bean array
     */
    @NotNull
    public static <Bean> Bean[] toBeanArray(@NotNull JSONArray jsonArray, @NotNull ToBean<Bean> toBean) throws JSONException {
        LinkedList<Bean> resultList = new LinkedList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            resultList.add(toBean.toBean(jsonArray.getJSONObject(i)));
        }
        //noinspection unchecked
        return (Bean[]) resultList.toArray();
    }

    /**
     * Convert JSONArray to the Bean array. If jsonArray is null or empty, or toBeanOrNull all return null, then finally return null
     */
    @Nullable
    public static <Bean> Bean[] toBeanArrayOrNull(@Nullable JSONArray jsonArray, @NotNull ToBeanOrNull<Bean> toBeanOrNull) throws JSONException {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        LinkedList<Bean> resultList = new LinkedList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            Bean bean = jsonObject != null ? toBeanOrNull.toBeanOrNull(jsonObject) : null;
            if (bean != null) {
                resultList.add(bean);
            }
        }
        //noinspection unchecked
        return resultList.size() > 0 ? (Bean[]) resultList.toArray() : null;
    }


    /**
     * Convert a JSONArray to a String array
     */
    @NotNull
    public static String[] toStringArray(@NotNull JSONArray jsonArray) throws JSONException {
        String[] dataArray = new String[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataArray[i] = jsonArray.get(i).toString();
        }
        return dataArray;
    }

    /**
     * Convert a JSONArray to a String array. If jsonArray is null or empty, or all item are not string, then finally return null
     */
    @Nullable
    public static String[] toStringArrayOrNull(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        LinkedList<String> dataList = new LinkedList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            Object item = jsonArray.opt(i);
            if (item != null) {
                dataList.add(item.toString());
            }
        }
        return dataList.size() > 0 ? dataList.toArray(new String[0]) : null;
    }


    /**
     * Convert a JSONArray to a String list
     */
    @NotNull
    public static ArrayList<String> toStringList(@NotNull JSONArray jsonArray) throws JSONException {
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataList.add(jsonArray.get(i).toString());
        }
        return dataList;
    }

    /**
     * Convert a JSONArray to a String list. If jsonArray is null or empty, or all item are not string, then finally return null
     */
    @Nullable
    public static ArrayList<String> toStringListOrNull(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            Object item = jsonArray.opt(i);
            if (item != null) {
                dataList.add(item.toString());
            }
        }
        return dataList.size() > 0 ? dataList : null;
    }


    /**
     * Convert a JSONArray to a int array
     */
    @NotNull
    public static int[] toIntArray(@NotNull JSONArray jsonArray) throws JSONException {
        int[] dataArray = new int[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataArray[i] = jsonArray.getInt(i);
        }
        return dataArray;
    }

    /**
     * Convert a JSONArray to a int array. If jsonArray is null or empty, or all item are not int, then finally return null
     */
    @Nullable
    public static int[] toIntArrayOrNull(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        LinkedList<Integer> dataList = new LinkedList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            int item = jsonArray.optInt(i, -9999);
            if (item != -9999 || jsonArray.optInt(i, -9998) != -9998) {
                dataList.add(item);
            }
        }
        if (dataList.size() > 0) {
            int[] dataArray = new int[dataList.size()];
            for (int i = 0, size = dataList.size(); i < size; i++) {
                dataArray[i] = dataList.get(i);
            }
            return dataArray;
        } else {
            return null;
        }
    }


    /**
     * Convert a JSONArray to a double array
     */
    @NotNull
    public static double[] toDoubleArray(@NotNull JSONArray jsonArray) throws JSONException {
        double[] dataArray = new double[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataArray[i] = jsonArray.getDouble(i);
        }
        return dataArray;
    }

    /**
     * Convert a JSONArray to a double array. If jsonArray is null or empty, or all item are not double, then finally return null
     */
    @Nullable
    public static double[] toDoubleArrayOrNull(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        LinkedList<Double> dataList = new LinkedList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            double item = jsonArray.optDouble(i, -9999.0);
            if (item != -9999.0 || jsonArray.optDouble(i, -9998.0) != -9998.0) {
                dataList.add(item);
            }
        }
        if (dataList.size() > 0) {
            double[] dataArray = new double[dataList.size()];
            for (int i = 0, size = dataList.size(); i < size; i++) {
                dataArray[i] = dataList.get(i);
            }
            return dataArray;
        } else {
            return null;
        }
    }


    /**
     * Convert a JSONArray to a long array
     */
    @NotNull
    public static long[] toLongArray(@NotNull JSONArray jsonArray) throws JSONException {
        long[] dataArray = new long[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataArray[i] = jsonArray.getLong(i);
        }
        return dataArray;
    }

    /**
     * Convert a JSONArray to a long array. If jsonArray is null or empty, or all item are not long, then finally return null
     */
    @Nullable
    public static long[] toLongArrayOrNull(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        LinkedList<Long> dataList = new LinkedList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            long item = jsonArray.optLong(i, -9999L);
            if (item != -9999L || jsonArray.optLong(i, -9998L) != -9998L) {
                dataList.add(item);
            }
        }
        if (dataList.size() > 0) {
            long[] dataArray = new long[dataList.size()];
            for (int i = 0, size = dataList.size(); i < size; i++) {
                dataArray[i] = dataList.get(i);
            }
            return dataArray;
        } else {
            return null;
        }
    }


    /**
     * Convert a JSONArray to a boolean array
     */
    @NotNull
    public static boolean[] toBooleanArray(@NotNull JSONArray jsonArray) throws JSONException {
        boolean[] dataArray = new boolean[jsonArray.length()];
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            dataArray[i] = jsonArray.getBoolean(i);
        }
        return dataArray;
    }

    /**
     * Convert a JSONArray to a boolean array. If jsonArray is null or empty, or all item are not boolean, then finally return null
     */
    @Nullable
    public static boolean[] toBooleanArrayOrNull(@Nullable JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0) return null;
        LinkedList<Boolean> dataList = new LinkedList<>();
        for (int i = 0, size = jsonArray.length(); i < size; i++) {
            boolean item = jsonArray.optBoolean(i, false);
            if (item || !jsonArray.optBoolean(i, true)) {
                dataList.add(item);
            }
        }
        if (dataList.size() > 0) {
            boolean[] dataArray = new boolean[dataList.size()];
            for (int i = 0, size = dataList.size(); i < size; i++) {
                dataArray[i] = dataList.get(i);
            }
            return dataArray;
        } else {
            return null;
        }
    }


    /* ************************************* opt and get ***************************************** */


    /**
     * Returns the value mapped by keys
     */
    @Nullable
    public static Object optWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
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
    public static String optStringWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys, @NotNull String defaultValue) {
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
    public static String optStringWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optStringWithKeys(jsonObject, keys, "");
    }

    /**
     * Returns the value mapped by keys and turn it into a int, or defaultValue if no such mapping exists.
     */
    public static int optIntWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys, int defaultValue) {
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
    public static int optIntWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optIntWithKeys(jsonObject, keys, 0);
    }

    /**
     * Returns the value mapped by keys and turn it into a long, or defaultValue if no such mapping exists.
     */
    public static long optLongWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys, long defaultValue) {
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
    public static long optLongWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optLongWithKeys(jsonObject, keys, 0L);
    }

    /**
     * Returns the value mapped by keys and turn it into a boolean, or defaultValue if no such mapping exists.
     */
    public static boolean optBooleanWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys, boolean defaultValue) {
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
    public static boolean optBooleanWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optBooleanWithKeys(jsonObject, keys, false);
    }

    /**
     * Returns the value mapped by keys and turn it into a double, or defaultValue if no such mapping exists.
     */
    public static double optDoubleWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys, double defaultValue) {
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
    public static double optDoubleWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        return optDoubleWithKeys(jsonObject, keys, 0.0);
    }

    /**
     * Returns the value mapped by keys, or null if no such mapping exists.
     */
    @Nullable
    public static JSONObject optJSONObjectWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        if (jsonObject != null) {
            JSONObject value;
            for (String key : keys) {
                value = jsonObject.optJSONObject(key);
                if (value != null) {
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
    public static JSONArray optJSONArrayWithKeys(@Nullable JSONObject jsonObject, @NotNull String[] keys) {
        if (jsonObject != null) {
            JSONArray value;
            for (String key : keys) {
                value = jsonObject.optJSONArray(key);
                if (value != null) {
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
    public static Object getWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                return value;
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
    public static String getStringWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                return value.toString();
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a int, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static int getIntWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                Integer integerValue = toInteger(value);
                if (integerValue != null) return integerValue;
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a long, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static long getLongWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                Long longValue = toLong(value);
                if (longValue != null) return longValue;
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a boolean, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static boolean getBooleanWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                Boolean booleanValue = toBoolean(value);
                if (booleanValue != null) return booleanValue;
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }

    /**
     * Returns the value mapped by keys if it exists and turn it into a double, coercing it if necessary, or throws if no such mapping exists.
     *
     * @throws JSONException if no such mapping exists.
     */
    public static double getDoubleWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        Object value;
        for (String key : keys) {
            value = jsonObject.opt(key);
            if (value != null && value != JSONObject.NULL) {
                Double doubleValue = toDouble(value);
                if (doubleValue != null) return doubleValue;
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
    public static JSONObject getJSONObjectWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        JSONObject value;
        for (String key : keys) {
            value = jsonObject.optJSONObject(key);
            if (value != null) {
                return value;
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
    public static JSONArray getJSONArrayWithKeys(@NotNull JSONObject jsonObject, @NotNull String[] keys) throws JSONException {
        JSONArray value;
        for (String key : keys) {
            value = jsonObject.optJSONArray(key);
            if (value != null) {
                return value;
            }
        }

        throw new JSONException("No value for " + Arrays.toString(keys));
    }


    /* ************************************* format ***************************************** */


    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public static String formatToString(@NotNull JSONObject jsonObject) {
        return new JsonFormatter().format(jsonObject);
    }

    /**
     * Formatted output for easy viewing. If jsonObject is null or empty, then return null
     */
    @Nullable
    public static String formatToStringOrNull(@Nullable JSONObject jsonObject) {
        return jsonObject != null && jsonObject.length() > 0 ? new JsonFormatter().format(jsonObject) : null;
    }

    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public static String formatToString(@NotNull JSONArray jsonArray) {
        return new JsonFormatter().format(jsonArray);
    }

    /**
     * Formatted output for easy viewing. If jsonArray is null or empty, then return null
     */
    @Nullable
    public static String formatToStringOrNull(@Nullable JSONArray jsonArray) {
        return jsonArray != null && jsonArray.length() > 0 ? new JsonFormatter().format(jsonArray) : null;
    }

    /**
     * Formatted output for easy viewing
     */
    @NotNull
    public static String formatJSON(@NotNull String json) throws JSONException {
        return new JsonFormatter().format(json);
    }

    /**
     * Formatted output for easy viewing. If the json string is empty, for example: ' ', 'null', '{}', '[]', then returns null
     */
    @Nullable
    public static String formatJSONOrNull(@Nullable String json) {
        try {
            return isJSON(json) && isNotEmptyJSON(json) ? new JsonFormatter().format(json) : null;
        } catch (JSONException e) {
            e.printStackTrace();
            new JSONException("error='" + e.getMessage() + "' , sourceJson='" + json + "'").printStackTrace();
            return null;
        }
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
}