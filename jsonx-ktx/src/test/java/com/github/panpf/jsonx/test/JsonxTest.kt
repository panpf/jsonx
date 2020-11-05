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

package com.github.panpf.jsonx.test

import com.github.panpf.jsonx.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class JsonxTest {

    @Test
    fun testIsJSON() {
        assertTrue("{\"age\":19}".isJSON())
        assertTrue(" {\"age\":19}  ".isJSON())
        assertTrue("{}".isJSON())
        assertTrue("{ }".isJSON())
        assertTrue(" {}  ".isJSON())
        assertFalse((null as String?).isJSON())
        assertFalse("".isJSON())
        assertFalse(" ".isJSON())
        assertFalse("  ".isJSON())
        assertFalse("   ".isJSON())
        assertFalse("null".isJSON())
        assertFalse("nu ll".isJSON())
        assertFalse(" null  ".isJSON())
        assertFalse(" nUll  ".isJSON())
        assertTrue("[]".isJSON())
        assertTrue("[ ]".isJSON())
        assertTrue(" []  ".isJSON())
        assertFalse("{".isJSON())
        assertFalse(" {  ".isJSON())
        assertFalse("}".isJSON())
        assertFalse(" }  ".isJSON())

        assertFalse("{\"age\":19}".isNotJSON())
        assertFalse(" {\"age\":19}  ".isNotJSON())
        assertFalse("{}".isNotJSON())
        assertFalse("{ }".isNotJSON())
        assertFalse(" {}  ".isNotJSON())
        assertTrue((null as String?).isNotJSON())
        assertTrue("".isNotJSON())
        assertTrue(" ".isNotJSON())
        assertTrue("  ".isNotJSON())
        assertTrue("   ".isNotJSON())
        assertTrue("null".isNotJSON())
        assertTrue("nu ll".isNotJSON())
        assertTrue(" null  ".isNotJSON())
        assertTrue(" nUll  ".isNotJSON())
        assertFalse("[]".isNotJSON())
        assertFalse("[ ]".isNotJSON())
        assertFalse(" []  ".isNotJSON())
        assertTrue("{".isNotJSON())
        assertTrue(" {  ".isNotJSON())
        assertTrue("}".isNotJSON())
        assertTrue(" }  ".isNotJSON())
    }

    @Test
    fun testIsJSONObject() {
        assertTrue("{\"age\":19}".isJSONObject())
        assertTrue(" {\"age\":19}  ".isJSONObject())
        assertTrue("{}".isJSONObject())
        assertTrue("{ }".isJSONObject())
        assertTrue(" {}  ".isJSONObject())
        assertFalse((null as String?).isJSONObject())
        assertFalse("".isJSONObject())
        assertFalse(" ".isJSONObject())
        assertFalse("  ".isJSONObject())
        assertFalse("   ".isJSONObject())
        assertFalse("null".isJSONObject())
        assertFalse("nu ll".isJSONObject())
        assertFalse(" null  ".isJSONObject())
        assertFalse(" nUll  ".isJSONObject())
        assertFalse("[]".isJSONObject())
        assertFalse("[ ]".isJSONObject())
        assertFalse(" []  ".isJSONObject())
        assertFalse("{".isJSONObject())
        assertFalse(" {  ".isJSONObject())
        assertFalse("}".isJSONObject())
        assertFalse(" }  ".isJSONObject())

        assertFalse("{\"age\":19}".isNotJSONObject())
        assertFalse(" {\"age\":19}  ".isNotJSONObject())
        assertFalse("{}".isNotJSONObject())
        assertFalse("{ }".isNotJSONObject())
        assertFalse(" {}  ".isNotJSONObject())
        assertTrue((null as String?).isNotJSONObject())
        assertTrue("".isNotJSONObject())
        assertTrue(" ".isNotJSONObject())
        assertTrue("  ".isNotJSONObject())
        assertTrue("   ".isNotJSONObject())
        assertTrue("null".isNotJSONObject())
        assertTrue("nu ll".isNotJSONObject())
        assertTrue(" null  ".isNotJSONObject())
        assertTrue(" nUll  ".isNotJSONObject())
        assertTrue("[]".isNotJSONObject())
        assertTrue("[ ]".isNotJSONObject())
        assertTrue(" []  ".isNotJSONObject())
        assertTrue("{".isNotJSONObject())
        assertTrue(" {  ".isNotJSONObject())
        assertTrue("}".isNotJSONObject())
        assertTrue(" }  ".isNotJSONObject())
    }

    @Test
    fun testIsJSONArray() {
        assertTrue("[19,20]".isJSONArray())
        assertTrue(" [19,20]  ".isJSONArray())
        assertTrue("[]".isJSONArray())
        assertTrue("[ ]".isJSONArray())
        assertTrue(" []  ".isJSONArray())
        assertFalse((null as String?).isJSONArray())
        assertFalse("".isJSONArray())
        assertFalse(" ".isJSONArray())
        assertFalse("  ".isJSONArray())
        assertFalse("   ".isJSONArray())
        assertFalse("null".isJSONArray())
        assertFalse("nu ll".isJSONArray())
        assertFalse(" null  ".isJSONArray())
        assertFalse(" nUll  ".isJSONArray())
        assertFalse("{}".isJSONArray())
        assertFalse("{ }".isJSONArray())
        assertFalse(" {}  ".isJSONArray())
        assertFalse("[".isJSONArray())
        assertFalse(" [  ".isJSONArray())
        assertFalse("]".isJSONArray())
        assertFalse(" ]  ".isJSONArray())

        assertFalse("[19,20]".isNotJSONArray())
        assertFalse(" [19,20]  ".isNotJSONArray())
        assertFalse("[]".isNotJSONArray())
        assertFalse("[ ]".isNotJSONArray())
        assertFalse(" []  ".isNotJSONArray())
        assertTrue((null as String?).isNotJSONArray())
        assertTrue("".isNotJSONArray())
        assertTrue(" ".isNotJSONArray())
        assertTrue("  ".isNotJSONArray())
        assertTrue("   ".isNotJSONArray())
        assertTrue("null".isNotJSONArray())
        assertTrue("nu ll".isNotJSONArray())
        assertTrue(" null  ".isNotJSONArray())
        assertTrue(" nUll  ".isNotJSONArray())
        assertTrue("{}".isNotJSONArray())
        assertTrue("{ }".isNotJSONArray())
        assertTrue(" {}  ".isNotJSONArray())
        assertTrue("[".isNotJSONArray())
        assertTrue(" [  ".isNotJSONArray())
        assertTrue("]".isNotJSONArray())
        assertTrue(" ]  ".isNotJSONArray())
    }

    @Test
    fun testIsEmptyJSON() {
        assertFalse("".isEmptyJSON())
        assertFalse(" ".isEmptyJSON())
        assertFalse("  ".isEmptyJSON())
        assertFalse("   ".isEmptyJSON())
        assertTrue("{}".isEmptyJSON())
        assertTrue("{ }".isEmptyJSON())
        assertTrue(" {}  ".isEmptyJSON())
        assertTrue("[]".isEmptyJSON())
        assertTrue("[ ]".isEmptyJSON())
        assertTrue(" []  ".isEmptyJSON())
        assertFalse("null".isEmptyJSON())
        assertFalse("nu ll".isEmptyJSON())
        assertFalse(" null  ".isEmptyJSON())
        assertFalse(" nUll  ".isEmptyJSON())
        assertFalse((null as String?).isEmptyJSON())
        assertFalse("{\"key\":\"value\"}".isEmptyJSON())
        assertFalse(" {\"key\":\"value\"}  ".isEmptyJSON())

        assertTrue("".isNotEmptyJSON())
        assertTrue(" ".isNotEmptyJSON())
        assertTrue("  ".isNotEmptyJSON())
        assertTrue("   ".isNotEmptyJSON())
        assertFalse("{}".isNotEmptyJSON())
        assertFalse("{ }".isNotEmptyJSON())
        assertFalse(" {}  ".isNotEmptyJSON())
        assertFalse("[]".isNotEmptyJSON())
        assertFalse("[ ]".isNotEmptyJSON())
        assertFalse(" []  ".isNotEmptyJSON())
        assertTrue("null".isNotEmptyJSON())
        assertTrue("nu ll".isNotEmptyJSON())
        assertTrue(" null  ".isNotEmptyJSON())
        assertTrue(" nUll  ".isNotEmptyJSON())
        assertTrue((null as String?).isNotEmptyJSON())
        assertTrue("{\"key\":\"value\"}".isNotEmptyJSON())
        assertTrue(" {\"key\":\"value\"}  ".isNotEmptyJSON())
    }

    @Test
    fun testIsEmptyJSONObject() {
        assertFalse("".isEmptyJSONObject())
        assertFalse(" ".isEmptyJSONObject())
        assertFalse("  ".isEmptyJSONObject())
        assertFalse("   ".isEmptyJSONObject())
        assertTrue("{}".isEmptyJSONObject())
        assertTrue("{ }".isEmptyJSONObject())
        assertTrue(" {}  ".isEmptyJSONObject())
        assertFalse("null".isEmptyJSONObject())
        assertFalse("nu ll".isEmptyJSONObject())
        assertFalse(" null  ".isEmptyJSONObject())
        assertFalse(" nUll  ".isEmptyJSONObject())
        assertFalse((null as String?).isEmptyJSONObject())
        assertFalse("[]".isEmptyJSONObject())
        assertFalse("[ ]".isEmptyJSONObject())
        assertFalse(" []  ".isEmptyJSONObject())
        assertFalse("{\"key\":\"value\"}".isEmptyJSONObject())
        assertFalse(" {\"key\":\"value\"}  ".isEmptyJSONObject())

        assertTrue("".isNotEmptyJSONObject())
        assertTrue(" ".isNotEmptyJSONObject())
        assertTrue("  ".isNotEmptyJSONObject())
        assertTrue("   ".isNotEmptyJSONObject())
        assertFalse("{}".isNotEmptyJSONObject())
        assertFalse("{ }".isNotEmptyJSONObject())
        assertFalse(" {}  ".isNotEmptyJSONObject())
        assertTrue("null".isNotEmptyJSONObject())
        assertTrue("nu ll".isNotEmptyJSONObject())
        assertTrue(" null  ".isNotEmptyJSONObject())
        assertTrue(" nUll  ".isNotEmptyJSONObject())
        assertTrue((null as String?).isNotEmptyJSONObject())
        assertTrue("[]".isNotEmptyJSONObject())
        assertTrue("[ ]".isNotEmptyJSONObject())
        assertTrue(" []  ".isNotEmptyJSONObject())
        assertTrue("{\"key\":\"value\"}".isNotEmptyJSONObject())
        assertTrue(" {\"key\":\"value\"}  ".isNotEmptyJSONObject())
    }

    @Test
    fun testIsEmptyJSONArray() {
        assertFalse("".isEmptyJSONArray())
        assertFalse(" ".isEmptyJSONArray())
        assertFalse("  ".isEmptyJSONArray())
        assertFalse("   ".isEmptyJSONArray())
        assertFalse("null".isEmptyJSONArray())
        assertFalse("nu ll".isEmptyJSONArray())
        assertFalse(" null  ".isEmptyJSONArray())
        assertFalse(" nUll  ".isEmptyJSONArray())
        assertFalse((null as String?).isEmptyJSONArray())
        assertTrue("[]".isEmptyJSONArray())
        assertTrue("[ ]".isEmptyJSONArray())
        assertTrue(" []  ".isEmptyJSONArray())
        assertFalse("{}".isEmptyJSONArray())
        assertFalse("{ }".isEmptyJSONArray())
        assertFalse(" {}  ".isEmptyJSONArray())
        assertFalse("{\"key\":\"value\"}".isEmptyJSONArray())
        assertFalse(" {\"key\":\"value\"}  ".isEmptyJSONArray())

        assertTrue("".isNotEmptyJSONArray())
        assertTrue(" ".isNotEmptyJSONArray())
        assertTrue("  ".isNotEmptyJSONArray())
        assertTrue("   ".isNotEmptyJSONArray())
        assertTrue("null".isNotEmptyJSONArray())
        assertTrue("nu ll".isNotEmptyJSONArray())
        assertTrue(" null  ".isNotEmptyJSONArray())
        assertTrue(" nUll  ".isNotEmptyJSONArray())
        assertTrue((null as String?).isNotEmptyJSONArray())
        assertFalse("[]".isNotEmptyJSONArray())
        assertFalse("[ ]".isNotEmptyJSONArray())
        assertFalse(" []  ".isNotEmptyJSONArray())
        assertTrue("{}".isNotEmptyJSONArray())
        assertTrue("{ }".isNotEmptyJSONArray())
        assertTrue(" {}  ".isNotEmptyJSONArray())
        assertTrue("{\"key\":\"value\"}".isNotEmptyJSONArray())
        assertTrue(" {\"key\":\"value\"}  ".isNotEmptyJSONArray())
    }

    @Test
    fun testToJSONObjectWithString() {
        try {
            "".toJSONObject()
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            "null".toJSONObject()
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            "{\"name:\"name19\",\"age\":19}".toJSONObject()
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            "{\"name\":\"name19\",\"age\":19}".toJSONObject()
        } catch (ignored: JSONException) {
            fail()
        }

        assertNull("".toJSONObjectOrNull())
        assertNull(null.toJSONObjectOrNull())
        assertNull("{\"name:\"name19\",\"age\":19}".toJSONObjectOrNull())
        assertEquals("{\"name\":\"name19\",\"age\":19}", "{\"name\":\"name19\",\"age\":19}".toJSONObjectOrNull()!!.toString())
    }

    @Test
    fun testToJSONObjectWithT() {
        try {
            Bean(19, "name19").toJSONObject { throw JSONException("test") }
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals(
                    "{\"name\":\"name19\",\"age\":19}",
                    Bean(19, "name19").toJSONObject { item: Bean ->
                        JSONObject().apply {
                            put("age", item.age)
                            put("name", item.name)
                        }
                    }.toString()
            )
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }

        val toJsonObjectOrNull: ToJSONObjectOrNull<Bean> = ToJSONObjectOrNull { item: Bean? ->
            if (item != null && item.age != 20) {
                JSONObject().apply {
                    put("age", item.age)
                    put("name", item.name)
                }
            } else {
                null
            }
        }
        try {
            assertNull(null.toJSONObjectOrNull(toJsonObjectOrNull))
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            assertNull(Bean(20, "name19").toJSONObjectOrNull(toJsonObjectOrNull))
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
        try {
            Bean(19, "name19").toJSONObjectOrNull { throw JSONException("test") }
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals("{\"name\":\"name19\",\"age\":19}", Bean(19, "name19").toJSONObjectOrNull(toJsonObjectOrNull)!!.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
            fail()
        }
    }

    @Test
    @Throws(JSONException::class)
    fun testToJSONArrayWithString() {
        try {
            "".toJSONArray()
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            "null".toJSONArray()
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            "[1,7\",8]".toJSONArray()
            fail()
        } catch (ignored: JSONException) {
        }
        try {
            "[1, \"7\", \"8]".toJSONArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertEquals("[1,7,8]", "[1, 7, 8]".toJSONArray().toString())

        assertNull("".toJSONArrayOrNull())
        assertNull("null".toJSONArrayOrNull())
        assertNull((null as String?).toJSONArrayOrNull())
        assertNull("[1,7\",8]".toJSONArrayOrNull())
        assertEquals("[1,7,8]", "[1, 7, 8]".toJSONArrayOrNull()!!.toString())
    }

    @Test
    @Throws(JSONException::class)
    fun testToJSONArrayWithListT() {
        val normalBeanList: List<Bean> = arrayListOf(Bean(19, "name19"), Bean(20, "name20"))
        val nullBeanList: List<Bean>? = null
        val emptyBeanList: List<Bean> = ArrayList(0)
        val allNullBeanList: List<Bean?> = arrayListOf<Bean?>(null, null)
        val partNullBeanList: List<Bean?> = arrayListOf(null, Bean(19, "name19"))
        val allUnsatisfiedBeanList: List<Bean> = arrayListOf(Bean(21, "name21"), Bean(22, "name22"))
        val partUnsatisfiedBeanList: List<Bean> = arrayListOf(Bean(20, "name20"), Bean(21, "name21"))
        val normalBeanListJsonArrayString = "[{\"name\":\"name19\",\"age\":19},{\"name\":\"name20\",\"age\":20}]"
        val normalBeanListJsonArrayToString = "[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\"]"
        val partUnsatisfiedBeanListJsonArrayStringOrNull = "[{\"name\":\"name20\",\"age\":20}]"
        val partNullBeanListJsonArrayStringOrNull = "[{\"name\":\"name19\",\"age\":19}]"
        val partNullBeanListJsonArrayToStringOrNull = "[\"Bean{name='name19', age=19}\"]"
        val toJsonObject: ToJSONObject<Bean> = ToJSONObject { item: Bean ->
            val jsonObject = JSONObject()
            jsonObject.put("age", item.age)
            jsonObject.put("name", item.name)
            jsonObject
        }
        val toJsonObjectOrNull: ToJSONObjectOrNull<Bean?> = ToJSONObjectOrNull { item: Bean? ->
            if (item != null && item.age <= 20) {
                JSONObject().apply {
                    put("age", item.age)
                    put("name", item.name)
                }
            } else {
                null
            }
        }
        assertEquals("[]", emptyBeanList.toJSONArray(toJsonObject).toString())
        assertEquals(normalBeanListJsonArrayString, normalBeanList.toJSONArray(toJsonObject).toString())
        assertNull(nullBeanList.toJSONArrayOrNull(toJsonObjectOrNull))
        assertNull(emptyBeanList.toJSONArrayOrNull(toJsonObjectOrNull))
        assertNull(allNullBeanList.toJSONArrayOrNull(toJsonObjectOrNull))
        assertEquals(partNullBeanListJsonArrayStringOrNull, partNullBeanList.toJSONArrayOrNull(toJsonObjectOrNull)!!.toString())
        assertNull(allUnsatisfiedBeanList.toJSONArrayOrNull(toJsonObjectOrNull))
        assertEquals(partUnsatisfiedBeanListJsonArrayStringOrNull, partUnsatisfiedBeanList.toJSONArrayOrNull(toJsonObjectOrNull)!!.toString())
        assertEquals(normalBeanListJsonArrayString, normalBeanList.toJSONArrayOrNull(toJsonObjectOrNull).toString())

        assertEquals("[]", emptyBeanList.toJSONArray().toString())
        assertEquals(normalBeanListJsonArrayToString, normalBeanList.toJSONArray().toString())
        assertNull(nullBeanList.toJSONArrayOrNull())
        assertNull(emptyBeanList.toJSONArrayOrNull())
        assertNull(allNullBeanList.toJSONArrayOrNull())
        assertEquals(partNullBeanListJsonArrayToStringOrNull, partNullBeanList.toJSONArrayOrNull()!!.toString())
        assertEquals(normalBeanListJsonArrayToString, normalBeanList.toJSONArrayOrNull()!!.toString())
    }

    @Test
    @Throws(JSONException::class)
    fun testToJSONArrayWithArrayT() {
        val normalBeanList: Array<Bean> = arrayOf(Bean(19, "name19"), Bean(20, "name20"))
        val nullBeanList: Array<Bean?>? = null
        val emptyBeanList: Array<Bean> = emptyArray()
        val allNullBeanList: Array<Bean?> = arrayOf(null, null)
        val partNullBeanList: Array<Bean?> = arrayOf(null, Bean(19, "name19"))
        val allUnsatisfiedBeanList: Array<Bean?> = arrayOf(Bean(21, "name21"), Bean(22, "name22"))
        val partUnsatisfiedBeanList: Array<Bean?> = arrayOf(Bean(20, "name20"), Bean(21, "name21"))
        val normalBeanListJsonArrayString = "[{\"name\":\"name19\",\"age\":19},{\"name\":\"name20\",\"age\":20}]"
        val normalBeanListJsonArrayToString = "[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\"]"
        val partUnsatisfiedBeanListJsonArrayStringOrNull = "[{\"name\":\"name20\",\"age\":20}]"
        val partNullBeanListJsonArrayStringOrNull = "[{\"name\":\"name19\",\"age\":19}]"
        val partNullBeanListJsonArrayToStringOrNull = "[\"Bean{name='name19', age=19}\"]"
        val toJsonObject: ToJSONObject<Bean> = ToJSONObject { item: Bean ->
            val jsonObject = JSONObject()
            jsonObject.put("age", item.age)
            jsonObject.put("name", item.name)
            jsonObject
        }
        val toJsonObjectOrNull: ToJSONObjectOrNull<Bean?> = ToJSONObjectOrNull { item: Bean? ->
            if (item != null && item.age <= 20) {
                JSONObject().apply {
                    put("age", item.age)
                    put("name", item.name)
                }
            } else {
                null
            }
        }
        val toJsonObjectOrNull2: ToJSONObjectOrNull<Bean> = ToJSONObjectOrNull { item: Bean? ->
            if (item != null && item.age <= 20) {
                JSONObject().apply {
                    put("age", item.age)
                    put("name", item.name)
                }
            } else {
                null
            }
        }
        assertEquals("[]", emptyBeanList.toJSONArray(toJsonObject).toString())
        assertEquals(normalBeanListJsonArrayString, normalBeanList.toJSONArray(toJsonObject).toString())
        assertNull(nullBeanList.toJSONArrayOrNull(toJsonObjectOrNull))
        assertNull(emptyBeanList.toJSONArrayOrNull(toJsonObjectOrNull2))
        assertNull(allNullBeanList.toJSONArrayOrNull(toJsonObjectOrNull))
        assertEquals(partNullBeanListJsonArrayStringOrNull, partNullBeanList.toJSONArrayOrNull(toJsonObjectOrNull)!!.toString())
        assertNull(allUnsatisfiedBeanList.toJSONArrayOrNull(toJsonObjectOrNull))
        assertEquals(partUnsatisfiedBeanListJsonArrayStringOrNull, partUnsatisfiedBeanList.toJSONArrayOrNull(toJsonObjectOrNull)!!.toString())
        assertEquals(normalBeanListJsonArrayString, normalBeanList.toJSONArrayOrNull(toJsonObjectOrNull2)!!.toString())

        assertEquals("[]", emptyBeanList.toJSONArray().toString())
        assertEquals(normalBeanListJsonArrayToString, normalBeanList.toJSONArray().toString())
        assertNull(nullBeanList.toJSONArrayOrNull())
        assertNull(emptyBeanList.toJSONArrayOrNull())
        assertNull(allNullBeanList.toJSONArrayOrNull())
        assertEquals(partNullBeanListJsonArrayToStringOrNull, partNullBeanList.toJSONArrayOrNull()!!.toString())
        assertEquals(normalBeanListJsonArrayToString, normalBeanList.toJSONArrayOrNull()!!.toString())
    }

    @Test
    fun testToJSONArrayWithIntArray() {
        assertEquals("[]", IntArray(0).toJSONArray().toString())
        assertEquals("[0,1,2,3,4,5]", intArrayOf(0, 1, 2, 3, 4, 5).toJSONArray().toString())

        assertNull((null as IntArray?).toJSONArrayOrNull())
        assertNull(IntArray(0).toJSONArrayOrNull())
        assertEquals("[0,1,2,3,4,5]", intArrayOf(0, 1, 2, 3, 4, 5).toJSONArrayOrNull()!!.toString())
    }

    @Test
    @Throws(JSONException::class)
    fun testToJSONArrayWithDoubleArray() {
        assertEquals("[]", DoubleArray(0).toJSONArray().toString())
        assertEquals("[0.1,1.1,2.1]", doubleArrayOf(0.1, 1.1, 2.1).toJSONArray().toString())

        assertNull((null as DoubleArray?).toJSONArrayOrNull())
        assertNull(DoubleArray(0).toJSONArrayOrNull())
        assertEquals("[0.1,1.1,2.1]", doubleArrayOf(0.1, 1.1, 2.1).toJSONArrayOrNull()!!.toString())
    }

    @Test
    fun testToJSONArrayWithLongArray() {
        assertEquals("[]", LongArray(0).toJSONArray().toString())
        assertEquals("[0,1,2,3,4,5]", longArrayOf(0L, 1L, 2L, 3L, 4L, 5L).toJSONArray().toString())

        assertNull((null as LongArray?).toJSONArrayOrNull())
        assertNull(LongArray(0).toJSONArrayOrNull())
        assertEquals("[0,1,2,3,4,5]", longArrayOf(0L, 1L, 2L, 3L, 4L, 5L).toJSONArrayOrNull()!!.toString())
    }

    @Test
    fun testToJSONArrayWithBooleanArray() {
        assertEquals("[]", BooleanArray(0).toJSONArray().toString())
        assertEquals("[false,true,true]", booleanArrayOf(false, true, true).toJSONArray().toString())

        assertNull((null as BooleanArray?).toJSONArrayOrNull())
        assertNull(BooleanArray(0).toJSONArrayOrNull())
        assertEquals("[false,true,true]", booleanArrayOf(false, true, true).toJSONArrayOrNull()!!.toString())
    }

    @Test
    @Throws(JSONException::class)
    fun testToBean() {
        val bean = Bean(20, "David")
        val toBean: ToBean<Bean> = ToBean { jsonObject: JSONObject -> Bean(jsonObject.getInt("age"), jsonObject.getString("name")) }
        val toBeanOrNull: ToBeanOrNull<Bean> = ToBeanOrNull { jsonObject: JSONObject? ->
            val age = jsonObject?.getInt("age") ?: -1
            if (jsonObject != null && age != 21) {
                Bean(age, jsonObject.getString("name"))
            } else {
                null
            }
        }

        val beanJsonObject = "{\"age\":20,\"name\":\"David\"}".toJSONObject()
        val errorJsonObject = "{}".toJSONObject()
        val unsatisfiedBeanJsonObject = "{\"age\":21,\"name\":\"David\"}".toJSONObject()
        assertEquals(bean, beanJsonObject.toBean(toBean))
        try {
            errorJsonObject.toBeanOrNull(toBeanOrNull)
            fail()
        } catch (ignored: JSONException) {
        }
        assertNull(null.toBeanOrNull(toBeanOrNull))
        assertNull(unsatisfiedBeanJsonObject.toBeanOrNull(toBeanOrNull))
        assertEquals(bean, beanJsonObject.toBeanOrNull(toBeanOrNull))

        val beanJson = "{\"age\":20,\"name\":\"David\"}"
        val errorJson = "{}"
        val unsatisfiedBeanJson = "{\"age\":21,\"name\":\"David\"}"
        assertEquals(bean, beanJson.jsonToBean(toBean))
        assertNull(errorJson.jsonToBeanOrNull(toBeanOrNull))
        assertNull(null.jsonToBeanOrNull(toBeanOrNull))
        assertNull(unsatisfiedBeanJson.jsonToBeanOrNull(toBeanOrNull))
        assertEquals(bean, beanJson.jsonToBeanOrNull(toBeanOrNull))
    }

    @Test
    @Throws(JSONException::class)
    fun testToBeanList() {
        val toBean: ToBean<Bean> = ToBean { jsonObject: JSONObject -> Bean(jsonObject.getInt("age"), jsonObject.getString("name")) }
        val toBeanOrNull: ToBeanOrNull<Bean> = ToBeanOrNull { jsonObject: JSONObject? ->
            val age = jsonObject?.getInt("age") ?: -1
            if (jsonObject != null && age != 21) {
                Bean(age, jsonObject.getString("name"))
            } else {
                null
            }
        }
        val toBeanOrNullAllNull: ToBeanOrNull<Bean> = ToBeanOrNull { null }

        val beanJsonArray = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]".toJSONArray()
        val errorJsonArray = JSONArray("[0,1]")
        try {
            errorJsonArray.toBeanList(toBean)
            fail()
        } catch (ignored: JSONException) {
        }
        assertEquals(ArrayList<Bean>(), JSONArray().toBeanList(toBean))
        assertEquals(listOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), beanJsonArray.toBeanList(toBean))
        assertNull(null.toBeanListOrNull(toBeanOrNull))
        assertNull(JSONArray().toBeanListOrNull(toBeanOrNull))
        assertNull(errorJsonArray.toBeanListOrNull(toBeanOrNull))
        assertNull(beanJsonArray.toBeanListOrNull(toBeanOrNullAllNull))
        assertEquals(listOf(Bean(20, "David"), Bean(22, "Ruth")), beanJsonArray.toBeanListOrNull(toBeanOrNull))

        val beanJson = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]"
        val errorJson = "[0,1]"
        try {
            errorJson.jsonToBeanList(toBean)
            fail()
        } catch (ignored: JSONException) {
        }
        assertEquals(ArrayList<Bean>(), "[]".jsonToBeanList(toBean))
        assertEquals(listOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), beanJson.jsonToBeanList(toBean))
        assertNull(null.jsonToBeanListOrNull(toBeanOrNull))
        assertNull("[]".jsonToBeanListOrNull(toBeanOrNull))
        assertNull(errorJson.jsonToBeanListOrNull(toBeanOrNull))
        assertNull(beanJson.jsonToBeanListOrNull(toBeanOrNullAllNull))
        assertEquals(listOf(Bean(20, "David"), Bean(22, "Ruth")), beanJson.jsonToBeanListOrNull(toBeanOrNull))
    }

    @Test
    @Throws(JSONException::class)
    fun testToBeanArray() {
        val toBean: ToBean<Bean> = ToBean { jsonObject: JSONObject -> Bean(jsonObject.getInt("age"), jsonObject.getString("name")) }
        val toBeanOrNull: ToBeanOrNull<Bean> = ToBeanOrNull { jsonObject: JSONObject? ->
            val age = jsonObject?.getInt("age") ?: -1
            if (jsonObject != null && age != 21) {
                Bean(age, jsonObject.getString("name"))
            } else {
                null
            }
        }
        val toBeanOrNullAllNull: ToBeanOrNull<Bean> = ToBeanOrNull { null }

        val beanJsonArray = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]".toJSONArray()
        val errorJsonArray = JSONArray("[0,1]")
        try {
            errorJsonArray.toBeanArray(toBean)
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(arrayOf<Bean>(), JSONArray().toBeanArray(toBean))
        assertArrayEquals(arrayOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), beanJsonArray.toBeanArray(toBean))
        assertNull(null.toBeanArrayOrNull(toBeanOrNull))
        assertNull(JSONArray().toBeanArrayOrNull(toBeanOrNull))
        assertNull(errorJsonArray.toBeanArrayOrNull(toBeanOrNull))
        assertNull(beanJsonArray.toBeanArrayOrNull(toBeanOrNullAllNull))
        assertArrayEquals(arrayOf(Bean(20, "David"), Bean(22, "Ruth")), beanJsonArray.toBeanArrayOrNull(toBeanOrNull))

        val beanJson = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]"
        val errorJson = "[0,1]"
        try {
            errorJson.jsonToBeanArray(toBean)
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(arrayOf<Bean>(), "[]".jsonToBeanArray(toBean))
        assertArrayEquals(arrayOf(Bean(20, "David"), Bean(21, "Kevin"), Bean(22, "Ruth")), beanJson.jsonToBeanArray(toBean))
        assertNull(null.jsonToBeanArrayOrNull(toBeanOrNull))
        assertNull("[]".jsonToBeanArrayOrNull(toBeanOrNull))
        assertNull(errorJson.jsonToBeanArrayOrNull(toBeanOrNull))
        assertNull(beanJson.jsonToBeanArrayOrNull(toBeanOrNullAllNull))
        assertArrayEquals(arrayOf(Bean(20, "David"), Bean(22, "Ruth")), beanJson.jsonToBeanArrayOrNull(toBeanOrNull))
    }

    @Test
    @Throws(JSONException::class)
    fun testToStringArray() {
        val stringJsonArray = JSONArray("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]")
        val haveNullJsonArray = JSONArray("[null]")
        assertArrayEquals(arrayOf("null"), haveNullJsonArray.toStringArray())
        assertArrayEquals(arrayOfNulls<String>(0), JSONArray().toStringArray())
        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), stringJsonArray.toStringArray())
        assertNull(null.toStringArrayOrNull())
        assertNull(JSONArray().toStringArrayOrNull())
        assertArrayEquals(arrayOf("null"), haveNullJsonArray.toStringArrayOrNull())
        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), stringJsonArray.toStringArrayOrNull())

        val stringJson = "[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]"
        val haveNullJson = "[null]"
        assertArrayEquals(arrayOf("null"), haveNullJson.jsonToStringArray())
        assertArrayEquals(arrayOfNulls<String>(0), "[]".jsonToStringArray())
        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), stringJson.jsonToStringArray())
        assertNull(null.jsonToStringArrayOrNull())
        assertNull("[]".jsonToStringArrayOrNull())
        assertArrayEquals(arrayOf("null"), haveNullJson.jsonToStringArrayOrNull())
        assertArrayEquals(arrayOf("0", "1", "2", "3", "4", "5"), stringJson.jsonToStringArrayOrNull())
    }

    @Test
    @Throws(JSONException::class)
    fun testToStringList() {
        val stringJsonArray = JSONArray("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]")
        val haveNullJsonArray = JSONArray("[null]")
        assertEquals(arrayListOf("null"), haveNullJsonArray.toStringList())
        assertEquals(arrayListOf<String>(), JSONArray().toStringList())
        assertEquals(arrayListOf("0", "1", "2", "3", "4", "5"), stringJsonArray.toStringList())
        assertNull(null.toStringListOrNull())
        assertNull(JSONArray().toStringListOrNull())
        assertEquals(arrayListOf("null"), haveNullJsonArray.toStringListOrNull())
        assertEquals(arrayListOf("0", "1", "2", "3", "4", "5"), stringJsonArray.toStringListOrNull())

        val stringJson = "[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]"
        val haveNullJson = "[null]"
        assertEquals(arrayListOf("null"), haveNullJson.jsonToStringList())
        assertEquals(arrayListOf<String>(), "[]".jsonToStringList())
        assertEquals(arrayListOf("0", "1", "2", "3", "4", "5"), stringJson.jsonToStringList())
        assertNull(null.jsonToStringListOrNull())
        assertNull("[]".jsonToStringListOrNull())
        assertEquals(arrayListOf("null"), haveNullJson.jsonToStringListOrNull())
        assertEquals(arrayListOf("0", "1", "2", "3", "4", "5"), stringJson.jsonToStringListOrNull())
    }

    @Test
    @Throws(JSONException::class)
    fun testToIntArray() {
        val intJsonArray = JSONArray("[0,1,2,3,4,-9999]")
        val haveNullJsonArray = JSONArray("[null]")
        try {
            haveNullJsonArray.toIntArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(IntArray(0), JSONArray().toIntArray())
        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, -9999), intJsonArray.toIntArray())
        assertNull(null.toIntArrayOrNull())
        assertNull(JSONArray().toIntArrayOrNull())
        assertNull(haveNullJsonArray.toIntArrayOrNull())
        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, -9999), intJsonArray.toIntArrayOrNull())

        val intJson = "[0,1,2,3,4,-9999]"
        val haveNullJson = "[null]"
        try {
            haveNullJson.jsonToIntArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(IntArray(0), "[]".jsonToIntArray())
        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, -9999), intJson.jsonToIntArray())
        assertNull(null.jsonToIntArrayOrNull())
        assertNull("[]".jsonToIntArrayOrNull())
        assertNull(haveNullJson.jsonToIntArrayOrNull())
        assertArrayEquals(intArrayOf(0, 1, 2, 3, 4, -9999), intJson.jsonToIntArrayOrNull())
    }

    @Test
    @Throws(JSONException::class)
    fun testToDoubleArray() {
        val doubleJsonArray = JSONArray("[0.1,1.1,2.1,3.1,4.1,-9999.0]")
        val haveNullJsonArray = JSONArray("[null]")
        try {
            haveNullJsonArray.toDoubleArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(DoubleArray(0), JSONArray().toDoubleArray(), 0.0)
        assertArrayEquals(doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, -9999.0), doubleJsonArray.toDoubleArray(), 0.0)
        assertNull(null.toDoubleArrayOrNull())
        assertNull(JSONArray().toDoubleArrayOrNull())
        assertNull(haveNullJsonArray.toDoubleArrayOrNull())
        assertArrayEquals(doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, -9999.0), doubleJsonArray.toDoubleArrayOrNull(), 0.0)

        val doubleJson = "[0.1,1.1,2.1,3.1,4.1,-9999.0]"
        val haveNullJson = "[null]"
        try {
            haveNullJson.jsonToDoubleArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(DoubleArray(0), "[]".jsonToDoubleArray(), 0.0)
        assertArrayEquals(doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, -9999.0), doubleJson.jsonToDoubleArray(), 0.0)
        assertNull(null.jsonToDoubleArrayOrNull())
        assertNull("[]".jsonToDoubleArrayOrNull())
        assertNull(haveNullJson.jsonToDoubleArrayOrNull())
        assertArrayEquals(doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, -9999.0), doubleJson.jsonToDoubleArrayOrNull(), 0.0)
    }

    @Test
    @Throws(JSONException::class)
    fun testToLongArray() {
        val longJsonArray = JSONArray("[0,1,2,3,4,-9999]")
        val haveNullJsonArray = JSONArray("[null]")
        try {
            haveNullJsonArray.toLongArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(LongArray(0), JSONArray().toLongArray())
        assertArrayEquals(longArrayOf(0, 1, 2, 3, 4, -9999L), longJsonArray.toLongArray())
        assertNull(null.toLongArrayOrNull())
        assertNull(JSONArray().toLongArrayOrNull())
        assertNull(haveNullJsonArray.toLongArrayOrNull())
        assertArrayEquals(longArrayOf(0, 1, 2, 3, 4, -9999L), longJsonArray.toLongArrayOrNull())

        val longJson = "[0,1,2,3,4,-9999]"
        val haveNullJson = "[null]"
        try {
            haveNullJson.jsonToLongArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(LongArray(0), "[]".jsonToLongArray())
        assertArrayEquals(longArrayOf(0, 1, 2, 3, 4, -9999L), longJson.jsonToLongArray())
        assertNull(null.jsonToLongArrayOrNull())
        assertNull("[]".jsonToLongArrayOrNull())
        assertNull(haveNullJson.jsonToLongArrayOrNull())
        assertArrayEquals(longArrayOf(0, 1, 2, 3, 4, -9999L), longJson.jsonToLongArrayOrNull())
    }

    @Test
    @Throws(JSONException::class)
    fun testToBooleanArray() {
        val booleanJsonArray = JSONArray("[false,true,true,false,false,true]")
        val haveNullJsonArray = JSONArray("[null]")
        try {
            haveNullJsonArray.toBooleanArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(BooleanArray(0), JSONArray().toBooleanArray())
        assertArrayEquals(booleanArrayOf(false, true, true, false, false, true), booleanJsonArray.toBooleanArray())
        assertNull(null.toBooleanArrayOrNull())
        assertNull(JSONArray().toBooleanArrayOrNull())
        assertNull(haveNullJsonArray.toBooleanArrayOrNull())
        assertArrayEquals(booleanArrayOf(false, true, true, false, false, true), booleanJsonArray.toBooleanArrayOrNull())

        val booleanJson = "[false,true,true,false,false,true]"
        val haveNullJson = "[null]"
        try {
            haveNullJson.jsonToBooleanArray()
            fail()
        } catch (ignored: JSONException) {
        }
        assertArrayEquals(BooleanArray(0), "[]".jsonToBooleanArray())
        assertArrayEquals(booleanArrayOf(false, true, true, false, false, true), booleanJson.jsonToBooleanArray())
        assertNull(null.jsonToBooleanArrayOrNull())
        assertNull("[]".jsonToBooleanArrayOrNull())
        assertNull(haveNullJson.jsonToBooleanArrayOrNull())
        assertArrayEquals(booleanArrayOf(false, true, true, false, false, true), booleanJson.jsonToBooleanArrayOrNull())
    }

    @Test
    @Throws(JSONException::class)
    fun testOptWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("name", JSONObject.NULL)
        jsonObject.put("name1", "Tony")
        assertEquals("Tony", Objects.requireNonNull(jsonObject.optWithKeys(arrayOf("name", "name0", "name1"))).toString())
        assertNull(jsonObject.optWithKeys(arrayOf("name", "name0", "name4")))
        assertNull(null.optWithKeys(arrayOf("name", "name0", "name4")))
    }

    @Test
    @Throws(JSONException::class)
    fun testOptStringWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("name", JSONObject.NULL)
        jsonObject.put("name1", "Tony")
        assertEquals("Tony", jsonObject.optStringWithKeys(arrayOf("name", "name0", "name1")))
        assertEquals("", jsonObject.optStringWithKeys(arrayOf("name", "name0", "name4")))
        assertEquals("unknown", jsonObject.optStringWithKeys(arrayOf("name", "name0", "name4"), "unknown"))
        assertEquals("unknown", null.optStringWithKeys(arrayOf("name", "name0", "name4"), "unknown"))
    }

    @Test
    @Throws(JSONException::class)
    fun testOptIntWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("age", JSONObject.NULL)
        jsonObject.put("age0", "age0")
        jsonObject.put("age1", 11)
        assertEquals(11, jsonObject.optIntWithKeys(arrayOf("age", "age0", "age1")).toLong())
        assertEquals(0, jsonObject.optIntWithKeys(arrayOf("age", "age0", "age4")).toLong())
        assertEquals(9, jsonObject.optIntWithKeys(arrayOf("age", "age0", "age4"), 9).toLong())
        assertEquals(9, null.optIntWithKeys(arrayOf("age", "age0", "age4"), 9).toLong())
    }

    @Test
    @Throws(JSONException::class)
    fun testOptLongWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("long", JSONObject.NULL)
        jsonObject.put("long0", "long0")
        jsonObject.put("long1", 21L)
        assertEquals(21L, jsonObject.optLongWithKeys(arrayOf("long", "long0", "long1")))
        assertEquals(0L, jsonObject.optLongWithKeys(arrayOf("long", "long0", "long4")))
        assertEquals(9L, jsonObject.optLongWithKeys(arrayOf("long", "long0", "long4"), 9L))
        assertEquals(9L, null.optLongWithKeys(arrayOf("long", "long0", "long4"), 9L))
    }

    @Test
    @Throws(JSONException::class)
    fun testOptBooleanWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("boolean", JSONObject.NULL)
        jsonObject.put("boolean0", "boolean0")
        jsonObject.put("boolean1", true)
        assertTrue(jsonObject.optBooleanWithKeys(arrayOf("boolean", "boolean0", "boolean1")))
        assertFalse(jsonObject.optBooleanWithKeys(arrayOf("boolean", "boolean0", "boolean4")))
        assertTrue(jsonObject.optBooleanWithKeys(arrayOf("boolean", "boolean0", "boolean4"), true))
        assertTrue(null.optBooleanWithKeys(arrayOf("boolean", "boolean0", "boolean4"), true))
    }

    @Test
    @Throws(JSONException::class)
    fun testOptDoubleWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("double", JSONObject.NULL)
        jsonObject.put("double0", "double0")
        jsonObject.put("double1", 21.0)
        assertEquals(21.0, jsonObject.optDoubleWithKeys(arrayOf("double", "double0", "double1")), 0.0)
        assertEquals(0.0, jsonObject.optDoubleWithKeys(arrayOf("double", "double0", "double4")), 0.0)
        assertEquals(9.0, jsonObject.optDoubleWithKeys(arrayOf("double", "double0", "double4"), 9.0), 0.0)
        assertEquals(9.0, null.optDoubleWithKeys(arrayOf("double", "double0", "double4"), 9.0), 0.0)
    }

    @Test
    @Throws(JSONException::class)
    fun testOptJSONObjectWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("jsonObject", JSONObject.NULL)
        val childJsonObject1 = JSONObject()
        childJsonObject1.put("child", "childValue")
        jsonObject.put("jsonObject1", childJsonObject1)
        assertNull(null.optJSONObjectWithKeys(arrayOf("jsonObject", "jsonObject1")))
        assertNull(jsonObject.optJSONObjectWithKeys(arrayOf("jsonObject", "jsonObject5")))
        assertEquals("{\"child\":\"childValue\"}", jsonObject.optJSONObjectWithKeys(arrayOf("jsonObject", "jsonObject1"))!!.toString())
    }

    @Test
    @Throws(JSONException::class)
    fun testOptJSONArrayWithKeys() {
        val jsonObject = JSONObject()
        jsonObject.put("jsonArray", JSONObject.NULL)
        val childJsonArray = JSONArray()
        childJsonArray.put("childArrayValue")
        jsonObject.put("jsonArray1", childJsonArray)
        assertNull(null.optJSONArrayWithKeys(arrayOf("jsonArray", "jsonArray4")))
        assertNull(jsonObject.optJSONArrayWithKeys(arrayOf("jsonArray", "jsonArray4")))
        assertEquals("[\"childArrayValue\"]", jsonObject.optJSONArrayWithKeys(arrayOf("jsonArray", "jsonArray1"))!!.toString())
    }

    @Test
    fun testGetWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("name", JSONObject.NULL)
            jsonObject.put("name1", "Tony")
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals("Tony", jsonObject.getWithKeys(arrayOf("name", "name1")).toString())
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getWithKeys(arrayOf("name", "name4"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    fun testGetStringWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("name", JSONObject.NULL)
            jsonObject.put("name1", "Tony")
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals("Tony", jsonObject.getStringWithKeys(arrayOf("name", "name1")))
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getStringWithKeys(arrayOf("name", "name4"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    fun testGetIntWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("age", JSONObject.NULL)
            jsonObject.put("age0", "age0")
            jsonObject.put("age1", 11)
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals(11, jsonObject.getIntWithKeys(arrayOf("age", "age0", "age1")).toLong())
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getIntWithKeys(arrayOf("age", "age0", "age4"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    fun testGetLongWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("long", JSONObject.NULL)
            jsonObject.put("long0", "long0")
            jsonObject.put("long1", 21L)
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals(21L, jsonObject.getLongWithKeys(arrayOf("long", "long0", "long1")))
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getLongWithKeys(arrayOf("long", "long0", "long4"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    fun testGetBooleanWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("boolean", JSONObject.NULL)
            jsonObject.put("boolean0", "boolean0")
            jsonObject.put("boolean1", true)
        } catch (ignored: JSONException) {
        }
        try {
            assertTrue(jsonObject.getBooleanWithKeys(arrayOf("boolean", "boolean0", "boolean1")))
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getBooleanWithKeys(arrayOf("boolean", "boolean0", "boolean4"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    fun testGetDoubleWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("double", JSONObject.NULL)
            jsonObject.put("double0", "double0")
            jsonObject.put("double1", 21.0)
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals(21.0, jsonObject.getDoubleWithKeys(arrayOf("double", "double0", "double1")), 0.0)
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getDoubleWithKeys(arrayOf("double", "double0", "double4"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    fun testGetJSONObjectWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("jsonObject", JSONObject.NULL)
            val childJsonObject1 = JSONObject()
            childJsonObject1.put("child", "childValue")
            jsonObject.put("jsonObject1", childJsonObject1)
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals("{\"child\":\"childValue\"}",
                    jsonObject.getJSONObjectWithKeys(arrayOf("jsonObject", "jsonObject1")).toString())
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getJSONObjectWithKeys(arrayOf("jsonObject", "jsonObject5"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    fun testGetJSONArrayWithKeys() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("jsonArray", JSONObject.NULL)
            val childJsonArray = JSONArray()
            childJsonArray.put("childArrayValue")
            jsonObject.put("jsonArray1", childJsonArray)
        } catch (ignored: JSONException) {
        }
        try {
            assertEquals("[\"childArrayValue\"]",
                    jsonObject.getJSONArrayWithKeys(arrayOf("jsonArray", "jsonArray1")).toString())
        } catch (e: JSONException) {
            fail()
        }
        try {
            jsonObject.getJSONArrayWithKeys(arrayOf("jsonArray", "jsonArray4"))
            fail()
        } catch (ignored: JSONException) {
        }
    }

    @Test
    @Throws(JSONException::class)
    fun testFormat() {
        val sourceJSONArrayString = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]"
        val sourceJSONObjectString = "{\"age\":20,\"name\":\"David\"}"
        val sourceArrayFormatResult = String(Base64.getMimeDecoder().decode("WwogICAgewogICAgICAgICJuYW1lIjoiRGF2aWQiLAogICAgICAgICJhZ2UiOjIwCiAgICB9LAog\nICAgewogICAgICAgICJuYW1lIjoiS2V2aW4iLAogICAgICAgICJhZ2UiOjIxCiAgICB9LAogICAg\newogICAgICAgICJuYW1lIjoiUnV0aCIsCiAgICAgICAgImFnZSI6MjIKICAgIH0KXQ==\n"))
        val sourceObjectFromResult = String(Base64.getMimeDecoder().decode("ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n"))

        assertEquals("{}", "{}".formatJSON())
        assertEquals("{ }", "{ }".formatJSON())
        assertEquals("[]", "[]".formatJSON())
        assertEquals("[ ]", "[ ]".formatJSON())
        try {
            "".formatJSON()
            fail()
        } catch (ignored: Exception) {
        }
        try {
            " ".formatJSON()
            fail()
        } catch (ignored: Exception) {
        }
        try {
            "null".formatJSON()
            fail()
        } catch (ignored: Exception) {
        }
        assertEquals(sourceArrayFormatResult, sourceJSONArrayString.formatJSON())
        assertEquals(sourceObjectFromResult, sourceJSONObjectString.formatJSON())
        assertNull("{}".formatJSONOrNull())
        assertNull("{ }".formatJSONOrNull())
        assertNull("[]".formatJSONOrNull())
        assertNull("[ ]".formatJSONOrNull())
        assertNull("".formatJSONOrNull())
        assertNull(" ".formatJSONOrNull())
        assertNull("null".formatJSONOrNull())
        assertEquals(sourceArrayFormatResult, sourceJSONArrayString.formatJSONOrNull())
        assertEquals(sourceObjectFromResult, sourceJSONObjectString.formatJSONOrNull())

        assertEquals("[]", JSONArray().formatToString())
        assertEquals(sourceArrayFormatResult, JSONArray(sourceJSONArrayString).formatToString())
        assertNull("[]", (null as JSONArray?).formatToStringOrNull())
        assertNull("[]", JSONArray().formatToStringOrNull())
        assertEquals(sourceArrayFormatResult, JSONArray(sourceJSONArrayString).formatToString())

        assertEquals("{}", JSONObject().formatToString())
        assertEquals(sourceObjectFromResult, JSONObject(sourceJSONObjectString).formatToString())
        assertNull((null as JSONObject?).formatToStringOrNull())
        assertNull(JSONObject().formatToStringOrNull())
        assertEquals(sourceObjectFromResult, JSONObject(sourceJSONObjectString).formatToString())
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
            return arrayOf(age, name).contentHashCode()
        }
    }
}

