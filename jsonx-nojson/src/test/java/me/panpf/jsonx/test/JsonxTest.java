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

package me.panpf.jsonx.test;

import me.panpf.javax.collections.Arrayx;
import me.panpf.javax.collections.Collectionx;
import me.panpf.javax.lang.Stringx;
import me.panpf.javax.util.Base64x;
import me.panpf.javax.util.Premisex;
import me.panpf.jsonx.Jsonx;
import me.panpf.jsonx.ToBean;
import me.panpf.jsonx.ToJsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class JsonxTest {

    @Test
    public void testIsEmpty() {
        assertTrue(Jsonx.isEmpty(""));
        assertTrue(Jsonx.isEmpty("{}"));
        assertTrue(Jsonx.isEmpty("[]"));
        assertTrue(Jsonx.isEmpty("null"));
        assertTrue(Jsonx.isEmpty(null));
        assertFalse(Jsonx.isEmpty("{\"key\":\"value\"}"));

        assertFalse(Jsonx.isNotEmpty(""));
        assertFalse(Jsonx.isNotEmpty("{}"));
        assertFalse(Jsonx.isNotEmpty("[]"));
        assertFalse(Jsonx.isNotEmpty("null"));
        assertFalse(Jsonx.isNotEmpty(null));
        assertTrue(Jsonx.isNotEmpty("{\"key\":\"value\"}"));
    }

    @Test
    public void testIsEmptyObject() {
        assertTrue(Jsonx.isEmptyObject(""));
        assertTrue(Jsonx.isEmptyObject("{}"));
        assertTrue(Jsonx.isEmptyObject("null"));
        assertTrue(Jsonx.isEmptyObject(null));
        assertFalse(Jsonx.isEmptyObject("[]"));
        assertFalse(Jsonx.isEmptyObject("{\"key\":\"value\"}"));

        assertFalse(Jsonx.isNotEmptyObject(""));
        assertFalse(Jsonx.isNotEmptyObject("{}"));
        assertFalse(Jsonx.isNotEmptyObject("null"));
        assertFalse(Jsonx.isNotEmptyObject(null));
        assertTrue(Jsonx.isNotEmptyObject("[]"));
        assertTrue(Jsonx.isNotEmptyObject("{\"key\":\"value\"}"));
    }

    @Test
    public void testIsEmptyArray() {
        assertTrue(Jsonx.isEmptyArray(""));
        assertTrue(Jsonx.isEmptyArray("null"));
        assertTrue(Jsonx.isEmptyArray(null));
        assertTrue(Jsonx.isEmptyArray("[]"));
        assertFalse(Jsonx.isEmptyArray("{}"));
        assertFalse(Jsonx.isEmptyArray("{\"key\":\"value\"}"));

        assertFalse(Jsonx.isNotEmptyArray(""));
        assertFalse(Jsonx.isNotEmptyArray("null"));
        assertFalse(Jsonx.isNotEmptyArray(null));
        assertFalse(Jsonx.isNotEmptyArray("[]"));
        assertTrue(Jsonx.isNotEmptyArray("{}"));
        assertTrue(Jsonx.isNotEmptyArray("{\"key\":\"value\"}"));
    }

    @Test
    public void testIsObject() {
        assertTrue(Jsonx.isObject("{\"age\":19}"));
        assertTrue(Jsonx.isObject("{}"));
        assertFalse(Jsonx.isObject(null));
        assertFalse(Jsonx.isObject(""));
        assertFalse(Jsonx.isObject("null"));
        assertFalse(Jsonx.isObject("[]"));
        assertFalse(Jsonx.isObject("{"));
        assertFalse(Jsonx.isObject("}"));

        assertFalse(Jsonx.isNotObject("{\"age\":19}"));
        assertFalse(Jsonx.isNotObject("{}"));
        assertTrue(Jsonx.isNotObject(null));
        assertTrue(Jsonx.isNotObject(""));
        assertTrue(Jsonx.isNotObject("null"));
        assertTrue(Jsonx.isNotObject("[]"));
        assertTrue(Jsonx.isNotObject("{"));
        assertTrue(Jsonx.isNotObject("}"));
    }

    @Test
    public void testIsArray() {
        assertTrue(Jsonx.isArray("[19,20]"));
        assertTrue(Jsonx.isArray("[]"));
        assertFalse(Jsonx.isArray(null));
        assertFalse(Jsonx.isArray(""));
        assertFalse(Jsonx.isArray("null"));
        assertFalse(Jsonx.isArray("{}"));
        assertFalse(Jsonx.isArray("["));
        assertFalse(Jsonx.isArray("]"));

        assertFalse(Jsonx.isNotArray("[19,20]"));
        assertFalse(Jsonx.isNotArray("[]"));
        assertTrue(Jsonx.isNotArray(null));
        assertTrue(Jsonx.isNotArray(""));
        assertTrue(Jsonx.isNotArray("null"));
        assertTrue(Jsonx.isNotArray("{}"));
        assertTrue(Jsonx.isNotArray("["));
        assertTrue(Jsonx.isNotArray("]"));
    }

    @Test
    public void testToJsonObject() {
        try {
            Jsonx.toJsonObject("");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJsonObject(null);
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Assert.assertEquals("{\"name\":\"name19\",\"age\":19}", Jsonx.toJsonObject("{\"name\":\"name19\",\"age\":19}").toString());
        } catch (JSONException ignored) {
            fail();
        }

        ToJsonObject<Bean> toJsonObject = new ToJsonObject<Bean>() {
            @Nullable
            @Override
            public JSONObject toJsonObject(@NotNull Bean item) throws JSONException {
                if (item.age != 20) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("age", item.age);
                    jsonObject.put("name", item.name);
                    return jsonObject;
                } else {
                    return null;
                }
            }
        };
        try {
            Assert.assertEquals("{\"name\":\"name19\",\"age\":19}", Jsonx.toJsonObject(new Bean(19, "name19"), toJsonObject).toString());
        } catch (JSONException ignored) {
            fail();
        }
    }

    @Test
    public void testToJsonArray() throws JSONException {
        List<Bean> beanList = Collectionx.arrayListOf(new Bean(19, "name19"), new Bean(20, "name20"), new Bean(21, "name21"));
        Bean[] beans = beanList.toArray(new Bean[0]);
        ToJsonObject<Bean> toJsonObject = new ToJsonObject<Bean>() {
            @Nullable
            @Override
            public JSONObject toJsonObject(@NotNull Bean item) throws JSONException {
                if (item.age != 20) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("age", item.age);
                    jsonObject.put("name", item.name);
                    return jsonObject;
                } else {
                    return null;
                }
            }
        };

        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", Jsonx.toJsonArray("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]").toString());
        try {
            Jsonx.toJsonArray("");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJsonArray((String) null);
            fail();
        } catch (JSONException ignored) {
        }

        //noinspection ConstantConditions
        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", Jsonx.toJsonArray(beanList, toJsonObject).toString());
        assertNull(Jsonx.toJsonArray((List<Bean>) null, toJsonObject));
        assertNull(Jsonx.toJsonArray(new ArrayList<Bean>(), toJsonObject));
        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", Jsonx.toJsonArrayString(beanList, toJsonObject));
        assertNull(Jsonx.toJsonArrayString((List<Bean>) null, toJsonObject));
        assertNull(Jsonx.toJsonArrayString(new ArrayList<Bean>(), toJsonObject));

        //noinspection ConstantConditions
        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", Jsonx.toJsonArray(beanList).toString());
        assertNull(Jsonx.toJsonArray((List<Bean>) null));
        assertNull(Jsonx.toJsonArray(new ArrayList<Bean>()));
        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", Jsonx.toJsonArrayString(beanList));
        assertNull(Jsonx.toJsonArrayString((List<Bean>) null));
        assertNull(Jsonx.toJsonArrayString(new ArrayList<Bean>()));

        //noinspection ConstantConditions
        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", Jsonx.toJsonArray(beans, toJsonObject).toString());
        assertNull(Jsonx.toJsonArray((Bean[]) null, toJsonObject));
        assertNull(Jsonx.toJsonArray(new Bean[0], toJsonObject));
        assertEquals("[{\"name\":\"name19\",\"age\":19},{\"name\":\"name21\",\"age\":21}]", Jsonx.toJsonArrayString(beans, toJsonObject));
        assertNull(Jsonx.toJsonArrayString((Bean[]) null, toJsonObject));
        assertNull(Jsonx.toJsonArrayString(new Bean[0], toJsonObject));

        //noinspection ConstantConditions
        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", Jsonx.toJsonArray(beans).toString());
        assertNull(Jsonx.toJsonArray((Bean[]) null));
        assertNull(Jsonx.toJsonArray(new Bean[0]));
        assertEquals("[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\",\"Bean{name='name21', age=21}\"]", Jsonx.toJsonArrayString(beans));
        assertNull(Jsonx.toJsonArrayString((Bean[]) null));
        assertNull(Jsonx.toJsonArrayString(new Bean[0]));

        //noinspection ConstantConditions
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJsonArray(new int[]{0, 1, 2, 3, 4, 5}).toString());
        assertNull(Jsonx.toJsonArray((int[]) null));
        assertNull(Jsonx.toJsonArray(new int[0]));
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJsonArrayString(new int[]{0, 1, 2, 3, 4, 5}));
        assertNull(Jsonx.toJsonArrayString((int[]) null));
        assertNull(Jsonx.toJsonArrayString(new int[0]));

        //noinspection ConstantConditions
        assertEquals("[0.1,1.1,2.1,3.1,4.1,5.1]", Jsonx.toJsonArray(new double[]{0.1, 1.1, 2.1, 3.1, 4.1, 5.1}).toString());
        assertNull(Jsonx.toJsonArray((double[]) null));
        assertNull(Jsonx.toJsonArray(new double[0]));
        assertEquals("[0.1,1.1,2.1,3.1,4.1,5.1]", Jsonx.toJsonArrayString(new double[]{0.1, 1.1, 2.1, 3.1, 4.1, 5.1}));
        assertNull(Jsonx.toJsonArrayString((double[]) null));
        assertNull(Jsonx.toJsonArrayString(new double[0]));

        //noinspection ConstantConditions
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJsonArray(new long[]{0L, 1L, 2L, 3L, 4L, 5L}).toString());
        assertNull(Jsonx.toJsonArray((long[]) null));
        assertNull(Jsonx.toJsonArray(new long[0]));
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJsonArrayString(new long[]{0L, 1L, 2L, 3L, 4L, 5L}));
        assertNull(Jsonx.toJsonArrayString((long[]) null));
        assertNull(Jsonx.toJsonArrayString(new long[0]));

        //noinspection ConstantConditions
        assertEquals("[false,true,true,false,false,true]", Jsonx.toJsonArray(new boolean[]{false, true, true, false, false, true}).toString());
        assertNull(Jsonx.toJsonArray((boolean[]) null));
        assertNull(Jsonx.toJsonArray(new boolean[0]));
        assertEquals("[false,true,true,false,false,true]", Jsonx.toJsonArrayString(new boolean[]{false, true, true, false, false, true}));
        assertNull(Jsonx.toJsonArrayString((boolean[]) null));
        assertNull(Jsonx.toJsonArrayString(new boolean[0]));
    }

    @Test
    public void testToBean() throws JSONException {
        String beanJson = "{\"age\":20,\"name\":\"David\"}";
        String beanArrayJson = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]";
        ToBean<Bean> toBean = new ToBean<Bean>() {
            @Nullable
            @Override
            public Bean toBean(@NotNull JSONObject jsonObject) throws JSONException {
                int age = jsonObject.getInt("age");
                if (age != 21) {
                    return new Bean(age, jsonObject.getString("name"));
                } else {
                    return null;
                }
            }
        };
        ToBean<Bean> toBeanAllNull = new ToBean<Bean>() {
            @Nullable
            @Override
            public Bean toBean(@NotNull JSONObject jsonObject) {
                return null;
            }
        };

        assertEquals(new Bean(20, "David"), Jsonx.toBean(new JSONObject(beanJson), toBean));
        assertNull(Jsonx.toBean((JSONObject) null, toBean));
        assertEquals(new Bean(20, "David"), Jsonx.toBean(beanJson, toBean));
        assertNull(Jsonx.toBean((String) null, toBean));
        assertNull(Jsonx.toBean("", toBean));
        assertNull(Jsonx.toBean("null", toBean));
        assertNull(Jsonx.toBean("{}", toBean));

        assertEquals(Collectionx.mutableListOf(new Bean(20, "David"), new Bean(22, "Ruth")), Jsonx.toBeanList(new JSONArray(beanArrayJson), toBean));
        assertNull(Jsonx.toBeanList((JSONArray) null, toBean));
        assertNull(Jsonx.toBeanList(new JSONArray(), toBean));
        assertNull(Jsonx.toBeanList(new JSONArray("[0,1]"), toBean));
        assertNull(Jsonx.toBeanList(new JSONArray(beanArrayJson), toBeanAllNull));
        assertEquals(Collectionx.mutableListOf(new Bean(20, "David"), new Bean(22, "Ruth")), Jsonx.toBeanList(beanArrayJson, toBean));
        assertNull(Jsonx.toBeanList((String) null, toBean));
        assertNull(Jsonx.toBeanList("[]", toBean));
        assertNull(Jsonx.toBeanList("[0,1]", toBean));
        assertNull(Jsonx.toBeanList(beanArrayJson, toBeanAllNull));

        String stringArrayJson = "[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]";
        assertEquals(Collectionx.mutableListOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringList(new JSONArray(stringArrayJson)));
        assertNull(Jsonx.toStringList((JSONArray) null));
        assertNull(Jsonx.toStringList(new JSONArray()));
        assertEquals(Collectionx.mutableListOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringList(stringArrayJson));
        assertNull(Jsonx.toStringList((String) null));
        assertNull(Jsonx.toStringList("[]"));
        assertNull(Jsonx.toStringList("null"));
        assertNull(Jsonx.toStringList(""));

        assertArrayEquals(Arrayx.arrayOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringArray(new JSONArray(stringArrayJson)));
        assertNull(Jsonx.toStringArray((JSONArray) null));
        assertNull(Jsonx.toStringArray(new JSONArray()));
        assertArrayEquals(Arrayx.arrayOf("0", "1", "2", "3", "4", "5"), Jsonx.toStringArray(stringArrayJson));
        assertNull(Jsonx.toStringArray((String) null));
        assertNull(Jsonx.toStringArray("[]"));
        assertNull(Jsonx.toStringArray("null"));
        assertNull(Jsonx.toStringArray(""));

        String intArrayJson = "[0,1,2,3,4,5]";
        assertArrayEquals(Arrayx.intArrayOf(0, 1, 2, 3, 4, 5), Jsonx.toIntArray(new JSONArray(intArrayJson)));
        assertNull(Jsonx.toIntArray((JSONArray) null));
        assertNull(Jsonx.toIntArray(new JSONArray()));
        assertArrayEquals(Arrayx.intArrayOf(0, 1, 2, 3, 4, 5), Jsonx.toIntArray(intArrayJson));
        assertNull(Jsonx.toIntArray((String) null));
        assertNull(Jsonx.toIntArray("[]"));
        assertNull(Jsonx.toIntArray("null"));
        assertNull(Jsonx.toIntArray(""));

        String doubleArrayJson = "[0.1,1.1,2.1,3.1,4.1,5.1]";
        assertArrayEquals(Arrayx.doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, 5.1), Jsonx.toDoubleArray(new JSONArray(doubleArrayJson)), 0.0);
        assertNull(Jsonx.toDoubleArray((JSONArray) null));
        assertNull(Jsonx.toDoubleArray(new JSONArray()));
        assertArrayEquals(Arrayx.doubleArrayOf(0.1, 1.1, 2.1, 3.1, 4.1, 5.1), Jsonx.toDoubleArray(doubleArrayJson), 0.0);
        assertNull(Jsonx.toDoubleArray((String) null));
        assertNull(Jsonx.toDoubleArray("[]"));
        assertNull(Jsonx.toDoubleArray("null"));
        assertNull(Jsonx.toDoubleArray(""));

        String longArrayJson = "[0,1,2,3,4,5]";
        assertArrayEquals(Arrayx.longArrayOf(0, 1, 2, 3, 4, 5), Jsonx.toLongArray(new JSONArray(longArrayJson)));
        assertNull(Jsonx.toLongArray((JSONArray) null));
        assertNull(Jsonx.toLongArray(new JSONArray()));
        assertArrayEquals(Arrayx.longArrayOf(0, 1, 2, 3, 4, 5), Jsonx.toLongArray(longArrayJson));
        assertNull(Jsonx.toLongArray((String) null));
        assertNull(Jsonx.toLongArray("[]"));
        assertNull(Jsonx.toLongArray("null"));
        assertNull(Jsonx.toLongArray(""));

        String booleanArrayJson = "[false,true,true,false,false,true]";
        assertArrayEquals(Arrayx.booleanArrayOf(false, true, true, false, false, true), Jsonx.toBooleanArray(new JSONArray(booleanArrayJson)));
        assertNull(Jsonx.toBooleanArray((JSONArray) null));
        assertNull(Jsonx.toBooleanArray(new JSONArray()));
        assertArrayEquals(Arrayx.booleanArrayOf(false, true, true, false, false, true), Jsonx.toBooleanArray(booleanArrayJson));
        assertNull(Jsonx.toBooleanArray((String) null));
        assertNull(Jsonx.toBooleanArray("[]"));
        assertNull(Jsonx.toBooleanArray("null"));
        assertNull(Jsonx.toBooleanArray(""));
    }

    @Test
    public void testOpt() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age1", 11);
        jsonObject.put("name1", "Tony");
        jsonObject.put("long1", 21L);
        jsonObject.put("boolean1", true);
        jsonObject.put("double1", 21.0);

        JSONObject childJsonObject1 = new JSONObject();
        childJsonObject1.put("child", "childValue");
        jsonObject.put("jsonObject1", childJsonObject1);

        JSONArray childJsonArray = new JSONArray();
        childJsonArray.put("childArrayValue");
        jsonObject.put("jsonArray1", childJsonArray);

        //noinspection ConstantConditions
        assertEquals("Tony", Jsonx.opt(jsonObject, Arrayx.arrayOf("age", "年龄", "name1")).toString());
        assertNull(Jsonx.opt(jsonObject, Arrayx.arrayOf("age", "年龄", "name4")));
        assertNull(Jsonx.opt(null, Arrayx.arrayOf("age", "年龄", "name4")));

        assertEquals("Tony", Jsonx.optString(jsonObject, Arrayx.arrayOf("age", "年龄", "name1")));
        assertEquals("", Jsonx.optString(jsonObject, Arrayx.arrayOf("age", "年龄", "name4")));
        assertEquals("unknown", Jsonx.optString(jsonObject, Arrayx.arrayOf("age", "年龄", "name4"), "unknown"));
        assertEquals("unknown", Jsonx.optString(null, Arrayx.arrayOf("age", "年龄", "name4"), "unknown"));

        assertEquals(11, Jsonx.optInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age1")));
        assertEquals(0, Jsonx.optInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age4")));
        assertEquals(9, Jsonx.optInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age4"), 9));
        assertEquals(9, Jsonx.optInt(null, Arrayx.arrayOf("age", "年龄", "age4"), 9));

        assertEquals(21L, Jsonx.optLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long1")));
        assertEquals(0L, Jsonx.optLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long4")));
        assertEquals(9L, Jsonx.optLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long4"), 9L));
        assertEquals(9L, Jsonx.optLong(null, Arrayx.arrayOf("long", "年龄", "long4"), 9L));

        assertTrue(Jsonx.optBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean1")));
        assertFalse(Jsonx.optBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean4")));
        assertTrue(Jsonx.optBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean4"), true));
        assertTrue(Jsonx.optBoolean(null, Arrayx.arrayOf("boolean", "年龄", "boolean4"), true));

        assertEquals(21.0, Jsonx.optDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double1")), 0.0);
        assertEquals(0.0, Jsonx.optDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double4")), 0.0);
        assertEquals(9.0, Jsonx.optDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double4"), 9.0), 0.0);
        assertEquals(9.0, Jsonx.optDouble(null, Arrayx.arrayOf("double", "年龄", "double4"), 9.0), 0.0);

        assertEquals("{\"child\":\"childValue\"}", Premisex.requireNotNull(Jsonx.optJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject1"))).toString());
        assertNull(Jsonx.optJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject5")));
        assertEquals("[\"childArrayValue\"]", Premisex.requireNotNull(Jsonx.optJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray1"))).toString());
        assertNull(Jsonx.optJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4")));
        assertNull(Jsonx.optJSONArray(null, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4")));
    }

    @Test
    public void testGet() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("age1", 11);
            jsonObject.put("name1", "Tony");
            jsonObject.put("long1", 21L);
            jsonObject.put("boolean1", true);
            jsonObject.put("double1", 21.0);

            JSONObject childJsonObject1 = new JSONObject();
            childJsonObject1.put("child", "childValue");
            jsonObject.put("jsonObject1", childJsonObject1);

            JSONArray childJsonArray = new JSONArray();
            childJsonArray.put("childArrayValue");
            jsonObject.put("jsonArray1", childJsonArray);
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("Tony", Jsonx.get(jsonObject, Arrayx.arrayOf("age", "年龄", "name1")).toString());
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.get(jsonObject, Arrayx.arrayOf("age", "年龄", "name4"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.get(null, Arrayx.arrayOf("age", "年龄", "name4"));
            fail();
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("Tony", Jsonx.getString(jsonObject, Arrayx.arrayOf("age", "年龄", "name1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getString(jsonObject, Arrayx.arrayOf("age", "年龄", "name4"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.getString(null, Arrayx.arrayOf("age", "年龄", "name4"));
            fail();
        } catch (JSONException ignored) {
        }


        try {
            assertEquals(11, Jsonx.getInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getInt(jsonObject, Arrayx.arrayOf("age", "年龄", "age4"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.getInt(null, Arrayx.arrayOf("age", "年龄", "age4"));
            fail();
        } catch (JSONException ignored) {
        }

        try {
            assertEquals(21L, Jsonx.getLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getLong(jsonObject, Arrayx.arrayOf("long", "年龄", "long4"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.getLong(null, Arrayx.arrayOf("long", "年龄", "long4"));
            fail();
        } catch (JSONException ignored) {
        }

        try {
            assertTrue(Jsonx.getBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getBoolean(jsonObject, Arrayx.arrayOf("boolean", "年龄", "boolean4"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.getBoolean(null, Arrayx.arrayOf("boolean", "年龄", "boolean4"));
            fail();
        } catch (JSONException ignored) {
        }

        try {
            assertEquals(21.0, Jsonx.getDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double1")), 0.0);
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getDouble(jsonObject, Arrayx.arrayOf("double", "年龄", "double4"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.getDouble(null, Arrayx.arrayOf("double", "年龄", "double4"));
            fail();
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("{\"child\":\"childValue\"}", Jsonx.getJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject1")).toString());
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getJSONObject(jsonObject, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject5"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.getJSONObject(null, Arrayx.arrayOf("jsonObject", "年龄", "jsonObject5"));
            fail();
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("[\"childArrayValue\"]", Jsonx.getJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray1")).toString());
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getJSONArray(jsonObject, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4"));
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.getJSONArray(null, Arrayx.arrayOf("jsonArray", "年龄", "jsonArray4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testFormat() throws JSONException {
        String sourceArray = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]";
        String sourceObject = "{\"age\":20,\"name\":\"David\"}";

        // 从 Android N 开始 JSONObject 内部实现由 HashMap 改为 LinkedHashMap，因此 json 字符串转为 JSONObject 后再转成字符串后由于顺序发生了变化导致内容不一样
        String sourceArrayFormatResultBase64 = "WwogICAgewogICAgICAgICJuYW1lIjoiRGF2aWQiLAogICAgICAgICJhZ2UiOjIwCiAgICB9LAog\nICAgewogICAgICAgICJuYW1lIjoiS2V2aW4iLAogICAgICAgICJhZ2UiOjIxCiAgICB9LAogICAg\newogICAgICAgICJuYW1lIjoiUnV0aCIsCiAgICAgICAgImFnZSI6MjIKICAgIH0KXQ==\n";

        assertEquals(Base64x.decodeToString(sourceArrayFormatResultBase64), Jsonx.format(sourceArray));
        assertEquals(Base64x.decodeToString(sourceArrayFormatResultBase64), Jsonx.format(new JSONArray(sourceArray)));
        assertEquals("[]", Jsonx.format((JSONArray) null));
        assertEquals("[]", Jsonx.format(new JSONArray()));

        // 从 Android N 开始 JSONObject 内部实现由 HashMap 改为 LinkedHashMap，因此 json 字符串转为 JSONObject 后再转成字符串后由于顺序发生了变化导致内容不一样
        String sourceObjectFromResultBase64 = "ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n";

        assertEquals(Base64x.decodeToString(sourceObjectFromResultBase64), Jsonx.format(sourceObject));
        assertEquals(Base64x.decodeToString(sourceObjectFromResultBase64), Jsonx.format(new JSONObject(sourceObject)));
        assertEquals("{}", Jsonx.format((JSONObject) null));
        assertEquals("{}", Jsonx.format((String) null));
        assertEquals("{}", Jsonx.format("{}"));
        assertEquals("[]", Jsonx.format("[]"));
        assertEquals("{}", Jsonx.format("null"));
    }

    public static class Bean {
        private String name;
        private int age;

        Bean(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bean bean = (Bean) o;
            return age == bean.age && Stringx.equals(name, bean.name);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{age, name});
        }
    }
}

