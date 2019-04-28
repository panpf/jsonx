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

package me.panpf.jsonxkt.test

import me.panpf.javaxkt.util.base64DecodeToString
import me.panpf.javaxkt.util.requireNotNull
import me.panpf.jsonx.ToBean
import me.panpf.jsonx.ToJsonObject
import me.panpf.jsonxkt.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class JsonxTest {

    @Test
    fun testIsEmpty() {
        assertTrue("".isEmptyJson())
        assertTrue("{}".isEmptyJson())
        assertTrue("[]".isEmptyJson())
        assertTrue("null".isEmptyJson())
        assertTrue((null as String?).isEmptyJson())
        assertFalse("{\"key\":\"value\"}".isEmptyJson())

        assertFalse("".isNotEmptyJson())
        assertFalse("{}".isNotEmptyJson())
        assertFalse("[]".isNotEmptyJson())
        assertFalse("null".isNotEmptyJson())
        assertFalse((null as String?).isNotEmptyJson())
        assertTrue("{\"key\":\"value\"}".isNotEmptyJson())
    }

    @Test
    fun testIsEmptyObject() {
        assertTrue("".isEmptyJsonObject())
        assertTrue("{}".isEmptyJsonObject())
        assertTrue("null".isEmptyJsonObject())
        assertTrue((null as String?).isEmptyJsonObject())
        assertFalse("[]".isEmptyJsonObject())
        assertFalse("{\"key\":\"value\"}".isEmptyJsonObject())

        assertFalse("".isNotEmptyJsonObject())
        assertFalse("{}".isNotEmptyJsonObject())
        assertFalse("null".isNotEmptyJsonObject())
        assertFalse((null as String?).isNotEmptyJsonObject())
        assertTrue("[]".isNotEmptyJsonObject())
        assertTrue("{\"key\":\"value\"}".isNotEmptyJsonObject())
    }

    @Test
    fun testIsEmptyArray() {
        assertTrue("".isEmptyJsonArray())
        assertTrue("null".isEmptyJsonArray())
        assertTrue((null as String?).isEmptyJsonArray())
        assertTrue("[]".isEmptyJsonArray())
        assertFalse("{}".isEmptyJsonArray())
        assertFalse("{\"key\":\"value\"}".isEmptyJsonArray())

        assertFalse("".isNotEmptyJsonArray())
        assertFalse("null".isNotEmptyJsonArray())
        assertFalse((null as String?).isNotEmptyJsonArray())
        assertFalse("[]".isNotEmptyJsonArray())
        assertTrue("{}".isNotEmptyJsonArray())
        assertTrue("{\"key\":\"value\"}".isNotEmptyJsonArray())
    }

    @Test
    fun testIsObject() {
        assertTrue("{\"age\":19}".isJsonObject())
        assertTrue("{}".isJsonObject())
        assertFalse((null as String?).isJsonObject())
        assertFalse("".isJsonObject())
        assertFalse("null".isJsonObject())
        assertFalse("[]".isJsonObject())
        assertFalse("{".isJsonObject())
        assertFalse("}".isJsonObject())

        assertFalse("{\"age\":19}".isNotJsonObject())
        assertFalse("{}".isNotJsonObject())
        assertTrue((null as String?).isNotJsonObject())
        assertTrue("".isNotJsonObject())
        assertTrue("null".isNotJsonObject())
        assertTrue("[]".isNotJsonObject())
        assertTrue("{".isNotJsonObject())
        assertTrue("}".isNotJsonObject())
    }

    @Test
    fun testIsArray() {
        assertTrue("[19,20]".isJsonArray())
        assertTrue("[]".isJsonArray())
        assertFalse((null as String?).isJsonArray())
        assertFalse("".isJsonArray())
        assertFalse("null".isJsonArray())
        assertFalse("{}".isJsonArray())
        assertFalse("]".isJsonArray())
        assertFalse("]".isJsonArray())

        assertFalse("[19,20]".isNotJsonArray())
        assertFalse("[]".isNotJsonArray())
        assertTrue((null as String?).isNotJsonArray())
        assertTrue("".isNotJsonArray())
        assertTrue("null".isNotJsonArray())
        assertTrue("{}".isNotJsonArray())
        assertTrue("[".isNotJsonArray())
        assertTrue("]".isNotJsonArray())
    }

    @Test
    fun testToJsonObject() {
        try {
            "".toJsonObject()
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as String?).toJsonObject()
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            Assert.assertEquals("{\"name\":\"name19\",\"age\":19}", "{\"name\":\"name19\",\"age\":19}".toJsonObject().toString())
        } catch (ignored: JSONException) {
            fail()
        }

        val toJsonObject = ToJsonObject<Bean> { item ->
            if (item.age != 20) {
                val jsonObject = JSONObject()
                jsonObject.put("age", item.age)
                jsonObject.put("name", item.name)
                jsonObject
            } else {
                null
            }
        }
        try {
            Assert.assertEquals("{\"name\":\"name19\",\"age\":19}", Bean(19, "name19").toJsonObject(toJsonObject)!!.toString())
        } catch (ignored: JSONException) {
            fail()
        }
    }

    @Test
    @Throws(JSONException::class)
    fun testToJsonArray() {
        val beanList = arrayListOf(Bean(19, "name19"), Bean(20, "name20"), Bean(21, "name21"))
        val beans = beanList.toTypedArray()
        val toJsonObject = ToJsonObject<Bean> { item ->
            if (item.age != 20) {
                val jsonObject = JSONObject()
                jsonObject.put("age", item.age)
                jsonObject.put("name", item.name)
                jsonObject
            } else {
                null
            }
        }

        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", "[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]".toJsonArray().toString())
        try {
            "".toJsonArray()
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as String?).toJsonArray()
            fail()
        } catch (ignored: JSONException) {
        }

        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", beanList.toJsonArray(toJsonObject)!!.toString())
        assertNull((null as List<Bean>?).toJsonArray(toJsonObject))
        assertNull(ArrayList<Bean>().toJsonArray(toJsonObject))
        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", beanList.toJsonArrayString(toJsonObject))
        assertNull((null as List<Bean>?).toJsonArrayString(toJsonObject))
        assertNull(ArrayList<Bean>().toJsonArrayString(toJsonObject))


        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", beanList.toJsonArray()!!.toString())
        assertNull((null as List<Bean>?).toJsonArray())
        assertNull((ArrayList<Bean>()).toJsonArray())
        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", beanList.toJsonArrayString())
        assertNull((null as List<Bean>?).toJsonArrayString())
        assertNull((ArrayList<Bean>()).toJsonArrayString())


        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", beans.toJsonArray(toJsonObject)!!.toString())
        assertNull((null as Array<Bean>?).toJsonArray(toJsonObject))
        assertNull(arrayOf<Bean>().toJsonArray(toJsonObject))
        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", beans.toJsonArrayString(toJsonObject))
        assertNull((null as Array<Bean>?).toJsonArrayString(toJsonObject))
        assertNull(arrayOf<Bean>().toJsonArrayString(toJsonObject))


        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", beans.toJsonArray()!!.toString())
        assertNull((null as Array<Bean>?).toJsonArray())
        assertNull(arrayOf<Bean>().toJsonArray())
        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", beans.toJsonArrayString())
        assertNull((null as Array<Bean>?).toJsonArrayString())
        assertNull(arrayOf<Bean>().toJsonArrayString())


        assertEquals("[0,1,2,3,4,5]", intArrayOf(0, 1, 2, 3, 4, 5).toJsonArray()!!.toString())
        assertNull((null as IntArray?).toJsonArray())
        assertNull(IntArray(0).toJsonArray())
        assertEquals("[0,1,2,3,4,5]", intArrayOf(0, 1, 2, 3, 4, 5).toJsonArrayString())
        assertNull((null as IntArray?).toJsonArrayString())
        assertNull(IntArray(0).toJsonArrayString())


        assertEquals("[0.1,1.1,2.1,3.1,4.1,5.1]", (doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, 5.1)).toJsonArray()!!.toString())
        assertNull((null as DoubleArray?).toJsonArray())
        assertNull(DoubleArray(0).toJsonArray())
        assertEquals("[0.1,1.1,2.1,3.1,4.1,5.1]", doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, 5.1).toJsonArrayString())
        assertNull((null as DoubleArray?).toJsonArrayString())
        assertNull(DoubleArray(0).toJsonArrayString())


        assertEquals("[0,1,2,3,4,5]", longArrayOf(0L, 1L, 2L, 3L, 4L, 5L).toJsonArray()!!.toString())
        assertNull((null as LongArray?).toJsonArray())
        assertNull(LongArray(0).toJsonArray())
        assertEquals("[0,1,2,3,4,5]", longArrayOf(0L, 1L, 2L, 3L, 4L, 5L).toJsonArrayString())
        assertNull((null as LongArray?).toJsonArrayString())
        assertNull(LongArray(0).toJsonArrayString())


        assertEquals("[false,true,true,false,false,true]", booleanArrayOf(false, true, true, false, false, true).toJsonArray()!!.toString())
        assertNull((null as BooleanArray?).toJsonArray())
        assertNull(BooleanArray(0).toJsonArray())
        assertEquals("[false,true,true,false,false,true]", booleanArrayOf(false, true, true, false, false, true).toJsonArrayString())
        assertNull((null as BooleanArray?).toJsonArrayString())
        assertNull(BooleanArray(0).toJsonArrayString())
    }

    @Test
    @Throws(JSONException::class)
    fun testToBean() {
        val beanJson = "{\"age\":20,\"name\":\"David\"}"
        val beanArrayJson = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]"
        val toBean = ToBean { jsonObject ->
            val age = jsonObject.getInt("age")
            if (age != 21) {
                Bean(age, jsonObject.getString("name"))
            } else {
                null
            }
        }
        val toBeanAllNull = ToBean<Bean> { null }

        assertEquals(Bean(20, "David"), JSONObject(beanJson).toBean(toBean))
        assertNull((null as JSONObject?).toBean(toBean))
        assertEquals(Bean(20, "David"), beanJson.jsonToBean(toBean))
        assertNull((null as String?).jsonToBean(toBean))
        assertNull("".jsonToBean(toBean))
        assertNull("null".jsonToBean(toBean))
        assertNull("{}".jsonToBean(toBean))

        assertEquals(mutableListOf(Bean(20, "David"), Bean(22, "Ruth")), JSONArray(beanArrayJson).toBeanList(toBean))
        assertNull((null as JSONArray?).toBeanList(toBean))
        assertNull((JSONArray()).toBeanList(toBean))
        assertNull(JSONArray("[0,1]").toBeanList(toBean))
        assertNull(JSONArray(beanArrayJson).toBeanList(toBeanAllNull))
        assertEquals(mutableListOf(Bean(20, "David"), Bean(22, "Ruth")), beanArrayJson.jsonToBeanList(toBean))
        assertNull((null as String?).jsonToBeanList(toBean))
        assertNull("[]".jsonToBeanList(toBean))
        assertNull("[0,1]".jsonToBeanList(toBean))
        assertNull(beanArrayJson.jsonToBeanList(toBeanAllNull))

        val stringArrayJson = "[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]"
        assertEquals(mutableListOf("0", "1", "2", "3", "4", "5"), JSONArray(stringArrayJson).toStringList())
        assertNull((null as JSONArray?).toStringList())
        assertNull(JSONArray().toStringList())
        assertEquals(mutableListOf("0", "1", "2", "3", "4", "5"), stringArrayJson.jsonToStringList())
        assertNull((null as String?).jsonToStringList())
        assertNull("[]".jsonToStringList())
        assertNull("null".jsonToStringList())
        assertNull("".jsonToStringList())

        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), JSONArray(stringArrayJson).toStringArray())
        assertNull((null as JSONArray?).toStringArray())
        assertNull(JSONArray().toStringArray())
        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), stringArrayJson.jsonToStringArray())
        assertNull((null as String?).jsonToStringArray())
        assertNull("[]".jsonToStringArray())
        assertNull("null".jsonToStringArray())
        assertNull("".jsonToStringArray())

        val intArrayJson = "[0,1,2,3,4,5]"
        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, 5), JSONArray(intArrayJson).toIntArray())
        assertNull((null as JSONArray?).toIntArray())
        assertNull(JSONArray().toIntArray())
        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, 5), intArrayJson.jsonToIntArray())
        assertNull((null as String?).jsonToIntArray())
        assertNull("[]".jsonToIntArray())
        assertNull("null".jsonToIntArray())
        assertNull("".jsonToIntArray())

        val doubleArrayJson = "[0.1,1.1,2.1,3.1,4.1,5.1]"
        assertArrayEquals(doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, 5.1), JSONArray(doubleArrayJson).toDoubleArray(), 0.0)
        assertNull((null as JSONArray?).toDoubleArray())
        assertNull(JSONArray().toDoubleArray())
        assertArrayEquals(doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, 5.1), doubleArrayJson.jsonToDoubleArray(), 0.0)
        assertNull((null as String?).jsonToDoubleArray())
        assertNull("[]".jsonToDoubleArray())
        assertNull("null".jsonToDoubleArray())
        assertNull("".jsonToDoubleArray())

        val longArrayJson = "[0,1,2,3,4,5]"
        assertArrayEquals(longArrayOf(0, 1, 2, 3, 4, 5), JSONArray(longArrayJson).toLongArray())
        assertNull((null as JSONArray?).toLongArray())
        assertNull(JSONArray().toLongArray())
        assertArrayEquals(longArrayOf(0, 1, 2, 3, 4, 5), longArrayJson.jsonToLongArray())
        assertNull((null as String?).jsonToLongArray())
        assertNull("[]".jsonToLongArray())
        assertNull("null".jsonToLongArray())
        assertNull("".jsonToLongArray())

        val booleanArrayJson = "[false,true,true,false,false,true]"
        assertArrayEquals(booleanArrayOf(false, true, true, false, false, true), JSONArray(booleanArrayJson).toBooleanArray())
        assertNull((null as JSONArray?).toBooleanArray())
        assertNull(JSONArray().toBooleanArray())
        assertArrayEquals(booleanArrayOf(false, true, true, false, false, true), booleanArrayJson.jsonToBooleanArray())
        assertNull((null as String?).jsonToBooleanArray())
        assertNull("[]".jsonToBooleanArray())
        assertNull("null".jsonToBooleanArray())
        assertNull("".jsonToBooleanArray())
    }

    @Test
    @Throws(JSONException::class)
    fun testOpt() {
        val jsonObject = JSONObject()
        jsonObject.put("age1", 11)
        jsonObject.put("name1", "Tony")
        jsonObject.put("long1", 21L)
        jsonObject.put("boolean1", true)
        jsonObject.put("double1", 21.0)

        val childJsonObject1 = JSONObject()
        childJsonObject1.put("child", "childValue")
        jsonObject.put("jsonObject1", childJsonObject1)

        val childJsonArray = JSONArray()
        childJsonArray.put("childArrayValue")
        jsonObject.put("jsonArray1", childJsonArray)


        assertEquals("Tony", jsonObject.opt(arrayOf("age", "年龄", "name1"))!!.toString())
        assertNull(jsonObject.opt(arrayOf("age", "年龄", "name4")))
        assertNull((null as JSONObject?).opt(arrayOf("age", "年龄", "name4")))

        assertEquals("Tony", jsonObject.optString(arrayOf("age", "年龄", "name1")))
        assertEquals("", jsonObject.optString(arrayOf("age", "年龄", "name4")))
        assertEquals("unknown", jsonObject.optString(arrayOf("age", "年龄", "name4"), "unknown"))
        assertEquals("unknown", (null as JSONObject?).optString(arrayOf("age", "年龄", "name4"), "unknown"))

        assertEquals(11, jsonObject.optInt(arrayOf("age", "年龄", "age1")).toLong())
        assertEquals(0, jsonObject.optInt(arrayOf("age", "年龄", "age4")).toLong())
        assertEquals(9, jsonObject.optInt(arrayOf("age", "年龄", "age4"), 9).toLong())
        assertEquals(9, (null as JSONObject?).optInt(arrayOf("age", "年龄", "age4"), 9).toLong())

        assertEquals(21L, jsonObject.optLong(arrayOf("long", "年龄", "long1")))
        assertEquals(0L, jsonObject.optLong(arrayOf("long", "年龄", "long4")))
        assertEquals(9L, jsonObject.optLong(arrayOf("long", "年龄", "long4"), 9L))
        assertEquals(9L, (null as JSONObject?).optLong(arrayOf("long", "年龄", "long4"), 9L))

        assertTrue(jsonObject.optBoolean(arrayOf("boolean", "年龄", "boolean1")))
        assertFalse(jsonObject.optBoolean(arrayOf("boolean", "年龄", "boolean4")))
        assertTrue(jsonObject.optBoolean(arrayOf("boolean", "年龄", "boolean4"), true))
        assertTrue((null as JSONObject?).optBoolean(arrayOf("boolean", "年龄", "boolean4"), true))

        assertEquals(21.0, jsonObject.optDouble(arrayOf("double", "年龄", "double1")), 0.0)
        assertEquals(0.0, jsonObject.optDouble(arrayOf("double", "年龄", "double4")), 0.0)
        assertEquals(9.0, jsonObject.optDouble(arrayOf("double", "年龄", "double4"), 9.0), 0.0)
        assertEquals(9.0, (null as JSONObject?).optDouble(arrayOf("double", "年龄", "double4"), 9.0), 0.0)

        assertEquals("{\"child\":\"childValue\"}", jsonObject.optJSONObject(arrayOf("jsonObject", "年龄", "jsonObject1")).requireNotNull().toString())
        assertNull(jsonObject.optJSONObject(arrayOf("jsonObject", "年龄", "jsonObject5")))
        assertEquals("[\"childArrayValue\"]", jsonObject.optJSONArray(arrayOf("jsonArray", "年龄", "jsonArray1")).requireNotNull().toString())
        assertNull(jsonObject.optJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4")))
        assertNull((null as JSONObject?).optJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4")))
    }

    @Test
    fun testGet() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("age1", 11)
            jsonObject.put("name1", "Tony")
            jsonObject.put("long1", 21L)
            jsonObject.put("boolean1", true)
            jsonObject.put("double1", 21.0)

            val childJsonObject1 = JSONObject()
            childJsonObject1.put("child", "childValue")
            jsonObject.put("jsonObject1", childJsonObject1)

            val childJsonArray = JSONArray()
            childJsonArray.put("childArrayValue")
            jsonObject.put("jsonArray1", childJsonArray)
        } catch (ignored: JSONException) {
        }

        try {
            assertEquals("Tony", jsonObject.get(arrayOf("age", "年龄", "name1")).toString())
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.get(arrayOf("age", "年龄", "name4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).get(arrayOf("age", "年龄", "name4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            assertEquals("Tony", jsonObject.getString(arrayOf("age", "年龄", "name1")))
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.getString(arrayOf("age", "年龄", "name4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).getString(arrayOf("age", "年龄", "name4"))
            fail()
        } catch (ignored: JSONException) {
        }


        try {
            assertEquals(11, jsonObject.getInt(arrayOf("age", "年龄", "age1")).toLong())
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.getInt(arrayOf("age", "年龄", "age4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).getInt(arrayOf("age", "年龄", "age4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            assertEquals(21L, jsonObject.getLong(arrayOf("long", "年龄", "long1")))
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.getLong(arrayOf("long", "年龄", "long4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).getLong(arrayOf("long", "年龄", "long4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            assertTrue(jsonObject.getBoolean(arrayOf("boolean", "年龄", "boolean1")))
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.getBoolean(arrayOf("boolean", "年龄", "boolean4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).getBoolean(arrayOf("boolean", "年龄", "boolean4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            assertEquals(21.0, jsonObject.getDouble(arrayOf("double", "年龄", "double1")), 0.0)
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.getDouble(arrayOf("double", "年龄", "double4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).getDouble(arrayOf("double", "年龄", "double4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            assertEquals("{\"child\":\"childValue\"}", jsonObject.getJSONObject(arrayOf("jsonObject", "年龄", "jsonObject1")).toString())
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.getJSONObject(arrayOf("jsonObject", "年龄", "jsonObject5"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).getJSONObject(arrayOf("jsonObject", "年龄", "jsonObject5"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            assertEquals("[\"childArrayValue\"]", jsonObject.getJSONArray(arrayOf("jsonArray", "年龄", "jsonArray1")).toString())
        } catch (e: JSONException) {
            fail()
        }

        try {
            jsonObject.getJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4"))
            fail()
        } catch (ignored: JSONException) {
        }

        try {
            (null as JSONObject?).getJSONArray(arrayOf("jsonArray", "年龄", "jsonArray4"))
            fail()
        } catch (ignored: JSONException) {
        }

    }

    @Test
    @Throws(JSONException::class)
    fun testFormat() {
        val sourceArray = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]"
        val sourceObject = "{\"age\":20,\"name\":\"David\"}"

        // 从 Android N 开始 JSONObject 内部实现由 HashMap 改为 LinkedHashMap，因此 json 字符串转为 JSONObject 后再转成字符串后由于顺序发生了变化导致内容不一样
        val sourceArrayFormatResultBase64 = "WwogICAgewogICAgICAgICJuYW1lIjoiRGF2aWQiLAogICAgICAgICJhZ2UiOjIwCiAgICB9LAog\nICAgewogICAgICAgICJuYW1lIjoiS2V2aW4iLAogICAgICAgICJhZ2UiOjIxCiAgICB9LAogICAg\newogICAgICAgICJuYW1lIjoiUnV0aCIsCiAgICAgICAgImFnZSI6MjIKICAgIH0KXQ==\n"

        assertEquals(sourceArrayFormatResultBase64.base64DecodeToString(), sourceArray.formatJson())
        assertEquals(sourceArrayFormatResultBase64.base64DecodeToString(), JSONArray(sourceArray).format())
        assertEquals("[]", (null as JSONArray?).format())
        assertEquals("[]", JSONArray().format())

        // 从 Android N 开始 JSONObject 内部实现由 HashMap 改为 LinkedHashMap，因此 json 字符串转为 JSONObject 后再转成字符串后由于顺序发生了变化导致内容不一样
        val sourceObjectFromResultBase64 = "ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n"

        assertEquals(sourceObjectFromResultBase64.base64DecodeToString(), sourceObject.formatJson())
        assertEquals(sourceObjectFromResultBase64.base64DecodeToString(), JSONObject(sourceObject).format())
        assertEquals("{}", (null as JSONObject?).format())
        assertEquals("{}", (null as String?).formatJson())
        assertEquals("{}", "{}".formatJson())
        assertEquals("[]", "[]".formatJson())
        assertEquals("{}", "null".formatJson())
    }

    class Bean internal constructor(val age: Int, val name: String) {

        override fun toString(): String {
            return "Bean{" +
                    "name='" + name + '\''.toString() +
                    ", age=" + age +
                    '}'.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false
            val bean = other as Bean?
            return age == bean!!.age && name == bean.name
        }

        override fun hashCode(): Int {
            return Arrays.hashCode(arrayOf(age, name))
        }
    }
}

