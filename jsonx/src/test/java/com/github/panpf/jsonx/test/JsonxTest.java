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

package com.github.panpf.jsonx.test;

import com.github.panpf.jsonx.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class JsonxTest {

    @Test
    public void testIsEmptyJSON() {
        assertTrue(Jsonx.isEmptyJSON(""));
        assertTrue(Jsonx.isEmptyJSON("{}"));
        assertTrue(Jsonx.isEmptyJSON("[]"));
        assertTrue(Jsonx.isEmptyJSON("null"));
        assertTrue(Jsonx.isEmptyJSON(null));
        assertFalse(Jsonx.isEmptyJSON("{\"key\":\"value\"}"));

        assertFalse(Jsonx.isNotEmptyJSON(""));
        assertFalse(Jsonx.isNotEmptyJSON("{}"));
        assertFalse(Jsonx.isNotEmptyJSON("[]"));
        assertFalse(Jsonx.isNotEmptyJSON("null"));
        assertFalse(Jsonx.isNotEmptyJSON(null));
        assertTrue(Jsonx.isNotEmptyJSON("{\"key\":\"value\"}"));
    }

    @Test
    public void testIsEmptyJSONObject() {
        assertTrue(Jsonx.isEmptyJSONObject(""));
        assertTrue(Jsonx.isEmptyJSONObject("{}"));
        assertTrue(Jsonx.isEmptyJSONObject("null"));
        assertTrue(Jsonx.isEmptyJSONObject(null));
        assertFalse(Jsonx.isEmptyJSONObject("[]"));
        assertFalse(Jsonx.isEmptyJSONObject("{\"key\":\"value\"}"));

        assertFalse(Jsonx.isNotEmptyJSONObject(""));
        assertFalse(Jsonx.isNotEmptyJSONObject("{}"));
        assertFalse(Jsonx.isNotEmptyJSONObject("null"));
        assertFalse(Jsonx.isNotEmptyJSONObject(null));
        assertTrue(Jsonx.isNotEmptyJSONObject("[]"));
        assertTrue(Jsonx.isNotEmptyJSONObject("{\"key\":\"value\"}"));
    }

    @Test
    public void testIsEmptyJSONArray() {
        assertTrue(Jsonx.isEmptyJSONArray(""));
        assertTrue(Jsonx.isEmptyJSONArray("null"));
        assertTrue(Jsonx.isEmptyJSONArray(null));
        assertTrue(Jsonx.isEmptyJSONArray("[]"));
        assertFalse(Jsonx.isEmptyJSONArray("{}"));
        assertFalse(Jsonx.isEmptyJSONArray("{\"key\":\"value\"}"));

        assertFalse(Jsonx.isNotEmptyJSONArray(""));
        assertFalse(Jsonx.isNotEmptyJSONArray("null"));
        assertFalse(Jsonx.isNotEmptyJSONArray(null));
        assertFalse(Jsonx.isNotEmptyJSONArray("[]"));
        assertTrue(Jsonx.isNotEmptyJSONArray("{}"));
        assertTrue(Jsonx.isNotEmptyJSONArray("{\"key\":\"value\"}"));
    }

    @Test
    public void testIsJSONObject() {
        assertTrue(Jsonx.isJSONObject("{\"age\":19}"));
        assertTrue(Jsonx.isJSONObject("{}"));
        assertFalse(Jsonx.isJSONObject(null));
        assertFalse(Jsonx.isJSONObject(""));
        assertFalse(Jsonx.isJSONObject("null"));
        assertFalse(Jsonx.isJSONObject("[]"));
        assertFalse(Jsonx.isJSONObject("{"));
        assertFalse(Jsonx.isJSONObject("}"));

        assertFalse(Jsonx.isNotJSONObject("{\"age\":19}"));
        assertFalse(Jsonx.isNotJSONObject("{}"));
        assertTrue(Jsonx.isNotJSONObject(null));
        assertTrue(Jsonx.isNotJSONObject(""));
        assertTrue(Jsonx.isNotJSONObject("null"));
        assertTrue(Jsonx.isNotJSONObject("[]"));
        assertTrue(Jsonx.isNotJSONObject("{"));
        assertTrue(Jsonx.isNotJSONObject("}"));
    }

    @Test
    public void testIsJSONArray() {
        assertTrue(Jsonx.isJSONArray("[19,20]"));
        assertTrue(Jsonx.isJSONArray("[]"));
        assertFalse(Jsonx.isJSONArray(null));
        assertFalse(Jsonx.isJSONArray(""));
        assertFalse(Jsonx.isJSONArray("null"));
        assertFalse(Jsonx.isJSONArray("{}"));
        assertFalse(Jsonx.isJSONArray("["));
        assertFalse(Jsonx.isJSONArray("]"));

        assertFalse(Jsonx.isNotJSONArray("[19,20]"));
        assertFalse(Jsonx.isNotJSONArray("[]"));
        assertTrue(Jsonx.isNotJSONArray(null));
        assertTrue(Jsonx.isNotJSONArray(""));
        assertTrue(Jsonx.isNotJSONArray("null"));
        assertTrue(Jsonx.isNotJSONArray("{}"));
        assertTrue(Jsonx.isNotJSONArray("["));
        assertTrue(Jsonx.isNotJSONArray("]"));
    }

    @Test
    public void testToJSONObjectWithString() {
        try {
            Jsonx.toJSONObject("");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJSONObject("null");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJSONObject("{\"name:\"name19\",\"age\":19}");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJSONObject("{\"name\":\"name19\",\"age\":19}");
        } catch (JSONException ignored) {
            fail();
        }

        Assert.assertNull(Jsonx.toJSONObjectOrNull(""));
        Assert.assertNull(Jsonx.toJSONObjectOrNull(null));
        Assert.assertNull(Jsonx.toJSONObjectOrNull("{\"name:\"name19\",\"age\":19}"));
        Assert.assertEquals(
                "{\"name\":\"name19\",\"age\":19}",
                Objects.requireNonNull(Jsonx.toJSONObjectOrNull("{\"name\":\"name19\",\"age\":19}")).toString()
        );
    }

    @Test
    public void testToJSONObjectWithT() {
        try {
            Jsonx.toJSONObject(new Bean(19, "name19"), item -> {
                throw new JSONException("test");
            });
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Assert.assertEquals(
                    "{\"name\":\"name19\",\"age\":19}",
                    Jsonx.toJSONObject(new Bean(19, "name19"), item -> {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("age", item.age);
                        jsonObject.put("name", item.name);
                        return jsonObject;
                    }).toString()
            );
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }

        ToJSONObjectOrNull<Bean> toJsonObjectOrNull = item -> {
            if (item != null && item.age != 20) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("age", item.age);
                jsonObject.put("name", item.name);
                return jsonObject;
            } else {
                return null;
            }
        };
        try {
            Assert.assertNull(Jsonx.toJSONObjectOrNull(null, toJsonObjectOrNull));
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Assert.assertNull(Jsonx.toJSONObjectOrNull(new Bean(20, "name19"), toJsonObjectOrNull));
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
        try {
            Jsonx.toJSONObjectOrNull(new Bean(19, "name19"), item -> {
                throw new JSONException("test");
            });
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Assert.assertEquals("{\"name\":\"name19\",\"age\":19}",
                    Objects.requireNonNull(Jsonx.toJSONObjectOrNull(new Bean(19, "name19"), toJsonObjectOrNull)).toString());
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testToJSONArrayWithString() throws JSONException {
        try {
            Jsonx.toJSONArray("");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJSONArray("null");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJSONArray("[1,7\",8]");
            fail();
        } catch (JSONException ignored) {
        }
        try {
            Jsonx.toJSONArray("[1, \"7\", \"8]");
            fail();
        } catch (JSONException ignored) {
        }
        assertEquals("[1,7,8]", Jsonx.toJSONArray("[1, 7, 8]").toString());

        assertNull(Jsonx.toJSONArrayOrNull(""));
        assertNull(Jsonx.toJSONArrayOrNull("null"));
        assertNull(Jsonx.toJSONArrayOrNull((String) null));
        assertNull(Jsonx.toJSONArrayOrNull("[1,7\",8]"));
        assertEquals("[1,7,8]", Objects.requireNonNull(Jsonx.toJSONArrayOrNull("[1, 7, 8]")).toString());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testToJSONArrayWithListT() throws JSONException {
        List<Bean> normalBeanList = arrayListOf(new Bean(19, "name19"), new Bean(20, "name20"));
        List<Bean> nullBeanList = null;
        List<Bean> emptyBeanList = new ArrayList<>(0);
        List<Bean> allNullBeanList = arrayListOf(null, null);
        List<Bean> partNullBeanList = arrayListOf(null, new Bean(19, "name19"));
        List<Bean> allUnsatisfiedBeanList = arrayListOf(new Bean(21, "name21"), new Bean(22, "name22"));
        List<Bean> partUnsatisfiedBeanList = arrayListOf(new Bean(20, "name20"), new Bean(21, "name21"));
        String normalBeanListJsonArrayString = "[{\"name\":\"name19\",\"age\":19},{\"name\":\"name20\",\"age\":20}]";
        String normalBeanListJsonArrayToString = "[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\"]";
        String partUnsatisfiedBeanListJsonArrayStringOrNull = "[{\"name\":\"name20\",\"age\":20}]";
        String partNullBeanListJsonArrayStringOrNull = "[{\"name\":\"name19\",\"age\":19}]";
        String partNullBeanListJsonArrayToStringOrNull = "[\"Bean{name='name19', age=19}\"]";
        ToJSONObject<Bean> toJsonObject = item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("age", item.age);
            jsonObject.put("name", item.name);
            return jsonObject;
        };
        ToJSONObjectOrNull<Bean> toJsonObjectOrNull = item -> {
            if (item != null && item.age <= 20) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("age", item.age);
                jsonObject.put("name", item.name);
                return jsonObject;
            } else {
                return null;
            }
        };

        assertEquals("[]", Jsonx.toJSONArray(emptyBeanList, toJsonObject).toString());
        assertEquals(normalBeanListJsonArrayString, Jsonx.toJSONArray(normalBeanList, toJsonObject).toString());

        assertNull(Jsonx.toJSONArrayOrNull(nullBeanList, toJsonObjectOrNull));
        assertNull(Jsonx.toJSONArrayOrNull(emptyBeanList, toJsonObjectOrNull));
        assertNull(Jsonx.toJSONArrayOrNull(allNullBeanList, toJsonObjectOrNull));
        assertEquals(
                partNullBeanListJsonArrayStringOrNull,
                Objects.requireNonNull(Jsonx.toJSONArrayOrNull(partNullBeanList, toJsonObjectOrNull)).toString()
        );
        assertNull(Jsonx.toJSONArrayOrNull(allUnsatisfiedBeanList, toJsonObjectOrNull));
        assertEquals(
                partUnsatisfiedBeanListJsonArrayStringOrNull,
                Objects.requireNonNull(Jsonx.toJSONArrayOrNull(partUnsatisfiedBeanList, toJsonObjectOrNull)).toString()
        );
        assertEquals(
                normalBeanListJsonArrayString,
                Objects.requireNonNull(Jsonx.toJSONArrayOrNull(normalBeanList, toJsonObjectOrNull)).toString()
        );

        assertEquals("[]", Jsonx.toJSONArray(emptyBeanList).toString());
        assertEquals(normalBeanListJsonArrayToString, Jsonx.toJSONArray(normalBeanList).toString());

        assertNull(Jsonx.toJSONArrayOrNull(nullBeanList));
        assertNull(Jsonx.toJSONArrayOrNull(emptyBeanList));
        assertNull(Jsonx.toJSONArrayOrNull(allNullBeanList));
        assertEquals(partNullBeanListJsonArrayToStringOrNull, Objects.requireNonNull(Jsonx.toJSONArrayOrNull(partNullBeanList)).toString());
        assertEquals(normalBeanListJsonArrayToString, Objects.requireNonNull(Jsonx.toJSONArrayOrNull(normalBeanList)).toString());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testToJSONArrayWithArrayT() throws JSONException {
        Bean[] normalBeanList = arrayListOf(new Bean(19, "name19"), new Bean(20, "name20")).toArray(new Bean[0]);
        Bean[] nullBeanList = null;
        Bean[] emptyBeanList = new ArrayList<Bean>(0).toArray(new Bean[0]);
        //noinspection SuspiciousToArrayCall
        Bean[] allNullBeanList = arrayListOf(null, null).toArray(new Bean[0]);
        Bean[] partNullBeanList = arrayListOf(null, new Bean(19, "name19")).toArray(new Bean[0]);
        Bean[] allUnsatisfiedBeanList = arrayListOf(new Bean(21, "name21"), new Bean(22, "name22")).toArray(new Bean[0]);
        Bean[] partUnsatisfiedBeanList = arrayListOf(new Bean(20, "name20"), new Bean(21, "name21")).toArray(new Bean[0]);
        String normalBeanListJsonArrayString = "[{\"name\":\"name19\",\"age\":19},{\"name\":\"name20\",\"age\":20}]";
        String normalBeanListJsonArrayToString = "[\"Bean{name='name19', age=19}\",\"Bean{name='name20', age=20}\"]";
        String partUnsatisfiedBeanListJsonArrayStringOrNull = "[{\"name\":\"name20\",\"age\":20}]";
        String partNullBeanListJsonArrayStringOrNull = "[{\"name\":\"name19\",\"age\":19}]";
        String partNullBeanListJsonArrayToStringOrNull = "[\"Bean{name='name19', age=19}\"]";
        ToJSONObject<Bean> toJsonObject = item -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("age", item.age);
            jsonObject.put("name", item.name);
            return jsonObject;
        };
        ToJSONObjectOrNull<Bean> toJsonObjectOrNull = item -> {
            if (item != null && item.age <= 20) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("age", item.age);
                jsonObject.put("name", item.name);
                return jsonObject;
            } else {
                return null;
            }
        };

        assertEquals("[]", Jsonx.toJSONArray(emptyBeanList, toJsonObject).toString());
        assertEquals(normalBeanListJsonArrayString, Jsonx.toJSONArray(normalBeanList, toJsonObject).toString());

        assertNull(Jsonx.toJSONArrayOrNull(nullBeanList, toJsonObjectOrNull));
        assertNull(Jsonx.toJSONArrayOrNull(emptyBeanList, toJsonObjectOrNull));
        assertNull(Jsonx.toJSONArrayOrNull(allNullBeanList, toJsonObjectOrNull));
        assertEquals(
                partNullBeanListJsonArrayStringOrNull,
                Objects.requireNonNull(Jsonx.toJSONArrayOrNull(partNullBeanList, toJsonObjectOrNull)).toString()
        );
        assertNull(Jsonx.toJSONArrayOrNull(allUnsatisfiedBeanList, toJsonObjectOrNull));
        assertEquals(
                partUnsatisfiedBeanListJsonArrayStringOrNull,
                Objects.requireNonNull(Jsonx.toJSONArrayOrNull(partUnsatisfiedBeanList, toJsonObjectOrNull)).toString()
        );
        assertEquals(
                normalBeanListJsonArrayString,
                Objects.requireNonNull(Jsonx.toJSONArrayOrNull(normalBeanList, toJsonObjectOrNull)).toString()
        );

        assertEquals("[]", Jsonx.toJSONArray(emptyBeanList).toString());
        assertEquals(normalBeanListJsonArrayToString, Jsonx.toJSONArray(normalBeanList).toString());

        assertNull(Jsonx.toJSONArrayOrNull(nullBeanList));
        assertNull(Jsonx.toJSONArrayOrNull(emptyBeanList));
        assertNull(Jsonx.toJSONArrayOrNull(allNullBeanList));
        assertEquals(partNullBeanListJsonArrayToStringOrNull, Objects.requireNonNull(Jsonx.toJSONArrayOrNull(partNullBeanList)).toString());
        assertEquals(normalBeanListJsonArrayToString, Objects.requireNonNull(Jsonx.toJSONArrayOrNull(normalBeanList)).toString());
    }

    @Test
    public void testToJSONArrayWithIntArray() {
        assertEquals("[]", Jsonx.toJSONArray(new int[0]).toString());
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJSONArray(new int[]{0, 1, 2, 3, 4, 5}).toString());

        assertNull(Jsonx.toJSONArrayOrNull((int[]) null));
        assertNull(Jsonx.toJSONArrayOrNull(new int[0]));
        assertEquals("[0,1,2,3,4,5]", Objects.requireNonNull(Jsonx.toJSONArrayOrNull(new int[]{0, 1, 2, 3, 4, 5})).toString());
    }

    @Test
    public void testToJSONArrayWithDoubleArray() throws JSONException {
        assertEquals("[]", Jsonx.toJSONArray(new double[0]).toString());
        assertEquals("[0.1,1.1,2.1]", Jsonx.toJSONArray(new double[]{0.1, 1.1, 2.1}).toString());

        assertNull(Jsonx.toJSONArrayOrNull((double[]) null));
        assertNull(Jsonx.toJSONArrayOrNull(new double[0]));
        assertEquals("[0.1,1.1,2.1]", Objects.requireNonNull(Jsonx.toJSONArrayOrNull(new double[]{0.1, 1.1, 2.1})).toString());
    }

    @Test
    public void testToJSONArrayWithLongArray() {
        assertEquals("[]", Jsonx.toJSONArray(new long[0]).toString());
        assertEquals("[0,1,2,3,4,5]", Jsonx.toJSONArray(new long[]{0L, 1L, 2L, 3L, 4L, 5L}).toString());

        assertNull(Jsonx.toJSONArrayOrNull((long[]) null));
        assertNull(Jsonx.toJSONArrayOrNull(new long[0]));
        assertEquals("[0,1,2,3,4,5]", Objects.requireNonNull(Jsonx.toJSONArrayOrNull(new long[]{0L, 1L, 2L, 3L, 4L, 5L})).toString());
    }

    @Test
    public void testToJSONArrayWithBooleanArray() {
        assertEquals("[]", Jsonx.toJSONArray(new boolean[0]).toString());
        assertEquals("[false,true,true]", Jsonx.toJSONArray(new boolean[]{false, true, true}).toString());

        assertNull(Jsonx.toJSONArrayOrNull((boolean[]) null));
        assertNull(Jsonx.toJSONArrayOrNull(new boolean[0]));
        assertEquals("[false,true,true]", Objects.requireNonNull(Jsonx.toJSONArrayOrNull(new boolean[]{false, true, true})).toString());
    }

    @Test
    public void testToBean() throws JSONException {
        Bean bean = new Bean(20, "David");
        JSONObject beanJsonObject = Jsonx.toJSONObject("{\"age\":20,\"name\":\"David\"}");
        JSONObject errorJsonObject = Jsonx.toJSONObject("{}");
        JSONObject unsatisfiedBeanJsonObject = Jsonx.toJSONObject("{\"age\":21,\"name\":\"David\"}");
        ToBean<Bean> toBean = jsonObject -> new Bean(jsonObject.getInt("age"), jsonObject.getString("name"));
        ToBeanOrNull<Bean> toBeanOrNull = jsonObject -> {
            int age = jsonObject != null ? jsonObject.getInt("age") : -1;
            if (jsonObject != null && age != 21) {
                return new Bean(age, jsonObject.getString("name"));
            } else {
                return null;
            }
        };

        assertEquals(bean, Jsonx.toBean(beanJsonObject, toBean));

        try {
            Jsonx.toBeanOrNull(errorJsonObject, toBeanOrNull);
            fail();
        } catch (JSONException ignored) {
        }
        assertNull(Jsonx.toBeanOrNull(null, toBeanOrNull));
        assertNull(Jsonx.toBeanOrNull(unsatisfiedBeanJsonObject, toBeanOrNull));
        assertEquals(bean, Jsonx.toBeanOrNull(beanJsonObject, toBeanOrNull));
    }

    @Test
    public void testToBeanList() throws JSONException {
        JSONArray beanJsonArray = Jsonx.toJSONArray("[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]");
        JSONArray errorJsonArray = new JSONArray("[0,1]");
        ToBean<Bean> toBean = jsonObject -> new Bean(jsonObject.getInt("age"), jsonObject.getString("name"));
        ToBeanOrNull<Bean> toBeanOrNull = jsonObject -> {
            int age = jsonObject != null ? jsonObject.getInt("age") : -1;
            if (jsonObject != null && age != 21) {
                return new Bean(age, jsonObject.getString("name"));
            } else {
                return null;
            }
        };
        ToBeanOrNull<Bean> toBeanOrNullAllNull = jsonObject -> null;

        try {
            Jsonx.toBeanList(errorJsonArray, toBean);
            fail();
        } catch (JSONException ignored) {
        }
        assertEquals(new ArrayList<Bean>(), Jsonx.toBeanList(new JSONArray(), toBean));
        assertEquals(arrayListOf(new Bean(20, "David"), new Bean(21, "Kevin"), new Bean(22, "Ruth")), Jsonx.toBeanList(beanJsonArray, toBean));

        assertNull(Jsonx.toBeanListOrNull(null, toBeanOrNull));
        assertNull(Jsonx.toBeanListOrNull(new JSONArray(), toBeanOrNull));
        assertNull(Jsonx.toBeanListOrNull(errorJsonArray, toBeanOrNull));
        assertNull(Jsonx.toBeanListOrNull(beanJsonArray, toBeanOrNullAllNull));
        assertEquals(arrayListOf(new Bean(20, "David"), new Bean(22, "Ruth")), Jsonx.toBeanListOrNull(beanJsonArray, toBeanOrNull));
    }

    @Test
    public void testToStringArray() throws JSONException {
        JSONArray stringJsonArray = new JSONArray("[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\"]");
        JSONArray haveNullJsonArray = new JSONArray();
        haveNullJsonArray.put((Object) null);
        try {
            Jsonx.toStringArray(haveNullJsonArray);
            fail();
        } catch (JSONException ignored) {
        }
        assertArrayEquals(new String[0], Jsonx.toStringArray(new JSONArray()));
        assertArrayEquals(new String[]{"0", "1", "2", "3", "4", "5"}, Jsonx.toStringArray(stringJsonArray));

        assertNull(Jsonx.toStringArrayOrNull(null));
        assertNull(Jsonx.toStringArrayOrNull(new JSONArray()));
        assertNull(Jsonx.toStringArrayOrNull(haveNullJsonArray));
        assertArrayEquals(new String[]{"0", "1", "2", "3", "4", "5"}, Jsonx.toStringArrayOrNull(stringJsonArray));
    }

    @Test
    public void testToIntArray() throws JSONException {
        JSONArray intJsonArray = new JSONArray("[0,1,2,3,4,-9999]");
        JSONArray haveNullJsonArray = new JSONArray();
        haveNullJsonArray.put((Object) null);
        try {
            Jsonx.toIntArray(haveNullJsonArray);
            fail();
        } catch (JSONException ignored) {
        }
        assertArrayEquals(new int[0], Jsonx.toIntArray(new JSONArray()));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, -9999}, Jsonx.toIntArray(intJsonArray));

        assertNull(Jsonx.toIntArrayOrNull(null));
        assertNull(Jsonx.toIntArrayOrNull(new JSONArray()));
        assertNull(Jsonx.toIntArrayOrNull(haveNullJsonArray));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, -9999}, Jsonx.toIntArrayOrNull(intJsonArray));
    }

    @Test
    public void testToDoubleArray() throws JSONException {
        JSONArray doubleJsonArray = new JSONArray("[0.1,1.1,2.1,3.1,4.1,-9999.0]");
        JSONArray haveNullJsonArray = new JSONArray();
        haveNullJsonArray.put((Object) null);
        try {
            Jsonx.toDoubleArray(haveNullJsonArray);
            fail();
        } catch (JSONException ignored) {
        }
        assertArrayEquals(new double[0], Jsonx.toDoubleArray(new JSONArray()), 0.0);
        assertArrayEquals(new double[]{0.1, 1.1, 2.1, 3.1, 4.1, -9999.0}, Jsonx.toDoubleArray(doubleJsonArray), 0.0);

        assertNull(Jsonx.toDoubleArrayOrNull(null));
        assertNull(Jsonx.toDoubleArrayOrNull(new JSONArray()));
        assertNull(Jsonx.toDoubleArrayOrNull(haveNullJsonArray));
        assertArrayEquals(new double[]{0.1, 1.1, 2.1, 3.1, 4.1, -9999.0}, Jsonx.toDoubleArrayOrNull(doubleJsonArray), 0.0);
    }

    @Test
    public void testToLongArray() throws JSONException {
        JSONArray longJsonArray = new JSONArray("[0,1,2,3,4,-9999]");
        JSONArray haveNullJsonArray = new JSONArray();
        haveNullJsonArray.put((Object) null);
        try {
            Jsonx.toLongArray(haveNullJsonArray);
            fail();
        } catch (JSONException ignored) {
        }
        assertArrayEquals(new long[0], Jsonx.toLongArray(new JSONArray()));
        assertArrayEquals(new long[]{0, 1, 2, 3, 4, -9999L}, Jsonx.toLongArray(longJsonArray));

        assertNull(Jsonx.toLongArrayOrNull(null));
        assertNull(Jsonx.toLongArrayOrNull(new JSONArray()));
        assertNull(Jsonx.toLongArrayOrNull(haveNullJsonArray));
        assertArrayEquals(new long[]{0, 1, 2, 3, 4, -9999L}, Jsonx.toLongArrayOrNull(longJsonArray));
    }

    @Test
    public void testToBooleanArray() throws JSONException {
        JSONArray booleanJsonArray = new JSONArray("[false,true,true,false,false,true]");
        JSONArray haveNullJsonArray = new JSONArray();
        haveNullJsonArray.put((Object) null);
        try {
            Jsonx.toBooleanArray(haveNullJsonArray);
            fail();
        } catch (JSONException ignored) {
        }
        assertArrayEquals(new boolean[0], Jsonx.toBooleanArray(new JSONArray()));
        assertArrayEquals(new boolean[]{false, true, true, false, false, true}, Jsonx.toBooleanArray(booleanJsonArray));

        assertNull(Jsonx.toBooleanArrayOrNull(null));
        assertNull(Jsonx.toBooleanArrayOrNull(new JSONArray()));
        assertNull(Jsonx.toBooleanArrayOrNull(haveNullJsonArray));
        assertArrayEquals(new boolean[]{false, true, true, false, false, true}, Jsonx.toBooleanArrayOrNull(booleanJsonArray));
    }

    @Test
    public void testOptWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", JSONObject.NULL);
        jsonObject.put("name1", "Tony");

        assertEquals("Tony", Objects.requireNonNull(Jsonx.optWithKeys(jsonObject, arrayOf("name", "name0", "name1"))).toString());
        assertNull(Jsonx.optWithKeys(jsonObject, arrayOf("name", "name0", "name4")));
        assertNull(Jsonx.optWithKeys(null, arrayOf("name", "name0", "name4")));
    }

    @Test
    public void testOptStringWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", JSONObject.NULL);
        jsonObject.put("name1", "Tony");

        assertEquals("Tony", Jsonx.optStringWithKeys(jsonObject, arrayOf("name", "name0", "name1")));
        assertEquals("", Jsonx.optStringWithKeys(jsonObject, arrayOf("name", "name0", "name4")));
        assertEquals("unknown", Jsonx.optStringWithKeys(jsonObject, arrayOf("name", "name0", "name4"), "unknown"));
        assertEquals("unknown", Jsonx.optStringWithKeys(null, arrayOf("name", "name0", "name4"), "unknown"));
    }

    @Test
    public void testOptIntWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", JSONObject.NULL);
        jsonObject.put("age0", "age0");
        jsonObject.put("age1", 11);

        assertEquals(11, Jsonx.optIntWithKeys(jsonObject, arrayOf("age", "age0", "age1")));
        assertEquals(0, Jsonx.optIntWithKeys(jsonObject, arrayOf("age", "age0", "age4")));
        assertEquals(9, Jsonx.optIntWithKeys(jsonObject, arrayOf("age", "age0", "age4"), 9));
        assertEquals(9, Jsonx.optIntWithKeys(null, arrayOf("age", "age0", "age4"), 9));
    }

    @Test
    public void testOptLongWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("long", JSONObject.NULL);
        jsonObject.put("long0", "long0");
        jsonObject.put("long1", 21L);

        assertEquals(21L, Jsonx.optLongWithKeys(jsonObject, arrayOf("long", "long0", "long1")));
        assertEquals(0L, Jsonx.optLongWithKeys(jsonObject, arrayOf("long", "long0", "long4")));
        assertEquals(9L, Jsonx.optLongWithKeys(jsonObject, arrayOf("long", "long0", "long4"), 9L));
        assertEquals(9L, Jsonx.optLongWithKeys(null, arrayOf("long", "long0", "long4"), 9L));
    }

    @Test
    public void testOptBooleanWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("boolean", JSONObject.NULL);
        jsonObject.put("boolean0", "boolean0");
        jsonObject.put("boolean1", true);

        assertTrue(Jsonx.optBooleanWithKeys(jsonObject, arrayOf("boolean", "boolean0", "boolean1")));
        assertFalse(Jsonx.optBooleanWithKeys(jsonObject, arrayOf("boolean", "boolean0", "boolean4")));
        assertTrue(Jsonx.optBooleanWithKeys(jsonObject, arrayOf("boolean", "boolean0", "boolean4"), true));
        assertTrue(Jsonx.optBooleanWithKeys(null, arrayOf("boolean", "boolean0", "boolean4"), true));
    }

    @Test
    public void testOptDoubleWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("double", JSONObject.NULL);
        jsonObject.put("double0", "double0");
        jsonObject.put("double1", 21.0);

        assertEquals(21.0, Jsonx.optDoubleWithKeys(jsonObject, arrayOf("double", "double0", "double1")), 0.0);
        assertEquals(0.0, Jsonx.optDoubleWithKeys(jsonObject, arrayOf("double", "double0", "double4")), 0.0);
        assertEquals(9.0, Jsonx.optDoubleWithKeys(jsonObject, arrayOf("double", "double0", "double4"), 9.0), 0.0);
        assertEquals(9.0, Jsonx.optDoubleWithKeys(null, arrayOf("double", "double0", "double4"), 9.0), 0.0);
    }

    @Test
    public void testOptJSONObjectWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonObject", JSONObject.NULL);
        JSONObject childJsonObject1 = new JSONObject();
        childJsonObject1.put("child", "childValue");
        jsonObject.put("jsonObject1", childJsonObject1);

        assertNull(Jsonx.optJSONObjectWithKeys(null, arrayOf("jsonObject", "jsonObject1")));
        assertNull(Jsonx.optJSONObjectWithKeys(jsonObject, arrayOf("jsonObject", "jsonObject5")));
        assertEquals("{\"child\":\"childValue\"}",
                Objects.requireNonNull(Jsonx.optJSONObjectWithKeys(jsonObject, arrayOf("jsonObject", "jsonObject1"))).toString());
    }

    @Test
    public void testOptJSONArrayWithKeys() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonArray", JSONObject.NULL);
        JSONArray childJsonArray = new JSONArray();
        childJsonArray.put("childArrayValue");
        jsonObject.put("jsonArray1", childJsonArray);

        assertNull(Jsonx.optJSONArrayWithKeys(null, arrayOf("jsonArray", "jsonArray4")));
        assertNull(Jsonx.optJSONArrayWithKeys(jsonObject, arrayOf("jsonArray", "jsonArray4")));
        assertEquals("[\"childArrayValue\"]",
                Objects.requireNonNull(Jsonx.optJSONArrayWithKeys(jsonObject, arrayOf("jsonArray", "jsonArray1"))).toString());
    }

    @Test
    public void testGetWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", JSONObject.NULL);
            jsonObject.put("name1", "Tony");
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("Tony", Jsonx.getWithKeys(jsonObject, arrayOf("name", "name1")).toString());
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getWithKeys(jsonObject, arrayOf("name", "name4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testGetStringWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", JSONObject.NULL);
            jsonObject.put("name1", "Tony");
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("Tony", Jsonx.getStringWithKeys(jsonObject, arrayOf("name", "name1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getStringWithKeys(jsonObject, arrayOf("name", "name4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testGetIntWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("age", JSONObject.NULL);
            jsonObject.put("age0", "age0");
            jsonObject.put("age1", 11);
        } catch (JSONException ignored) {
        }

        try {
            assertEquals(11, Jsonx.getIntWithKeys(jsonObject, arrayOf("age", "age0", "age1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getIntWithKeys(jsonObject, arrayOf("age", "age0", "age4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testGetLongWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("long", JSONObject.NULL);
            jsonObject.put("long0", "long0");
            jsonObject.put("long1", 21L);
        } catch (JSONException ignored) {
        }

        try {
            assertEquals(21L, Jsonx.getLongWithKeys(jsonObject, arrayOf("long", "long0", "long1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getLongWithKeys(jsonObject, arrayOf("long", "long0", "long4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testGetBooleanWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("boolean", JSONObject.NULL);
            jsonObject.put("boolean0", "boolean0");
            jsonObject.put("boolean1", true);
        } catch (JSONException ignored) {
        }

        try {
            assertTrue(Jsonx.getBooleanWithKeys(jsonObject, arrayOf("boolean", "boolean0", "boolean1")));
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getBooleanWithKeys(jsonObject, arrayOf("boolean", "boolean0", "boolean4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testGetDoubleWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("double", JSONObject.NULL);
            jsonObject.put("double0", "double0");
            jsonObject.put("double1", 21.0);
        } catch (JSONException ignored) {
        }

        try {
            assertEquals(21.0, Jsonx.getDoubleWithKeys(jsonObject, arrayOf("double", "double0", "double1")), 0.0);
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getDoubleWithKeys(jsonObject, arrayOf("double", "double0", "double4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testGetJSONObjectWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("jsonObject", JSONObject.NULL);
            JSONObject childJsonObject1 = new JSONObject();
            childJsonObject1.put("child", "childValue");
            jsonObject.put("jsonObject1", childJsonObject1);
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("{\"child\":\"childValue\"}",
                    Jsonx.getJSONObjectWithKeys(jsonObject, arrayOf("jsonObject", "jsonObject1")).toString());
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getJSONObjectWithKeys(jsonObject, arrayOf("jsonObject", "jsonObject5"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testGetJSONArrayWithKeys() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("jsonArray", JSONObject.NULL);
            JSONArray childJsonArray = new JSONArray();
            childJsonArray.put("childArrayValue");
            jsonObject.put("jsonArray1", childJsonArray);
        } catch (JSONException ignored) {
        }

        try {
            assertEquals("[\"childArrayValue\"]",
                    Jsonx.getJSONArrayWithKeys(jsonObject, arrayOf("jsonArray", "jsonArray1")).toString());
        } catch (JSONException e) {
            fail();
        }
        try {
            Jsonx.getJSONArrayWithKeys(jsonObject, arrayOf("jsonArray", "jsonArray4"));
            fail();
        } catch (JSONException ignored) {
        }
    }

    @Test
    public void testFormat() throws JSONException {
        String sourceArray = "[{\"age\":20,\"name\":\"David\"},{\"age\":21,\"name\":\"Kevin\"},{\"age\":22,\"name\":\"Ruth\"}]";
        String sourceObject = "{\"age\":20,\"name\":\"David\"}";
        String sourceArrayFormatResult = new String(Base64.getMimeDecoder().decode("WwogICAgewogICAgICAgICJuYW1lIjoiRGF2aWQiLAogICAgICAgICJhZ2UiOjIwCiAgICB9LAog\nICAgewogICAgICAgICJuYW1lIjoiS2V2aW4iLAogICAgICAgICJhZ2UiOjIxCiAgICB9LAogICAg\newogICAgICAgICJuYW1lIjoiUnV0aCIsCiAgICAgICAgImFnZSI6MjIKICAgIH0KXQ==\n"));
        String sourceObjectFromResult = new String(Base64.getMimeDecoder().decode("ewogICAgIm5hbWUiOiJEYXZpZCIsCiAgICAiYWdlIjoyMAp9\n"));

        assertEquals("{}", Jsonx.formatJSON("{}"));
        assertEquals("[]", Jsonx.formatJSON("[]"));
        assertEquals("{}", Jsonx.formatJSON("null"));
        assertEquals(sourceArrayFormatResult, Jsonx.formatJSON(sourceArray));
        assertEquals(sourceObjectFromResult, Jsonx.formatJSON(sourceObject));
        assertNull(Jsonx.formatJSONOrNull("{}"));
        assertNull(Jsonx.formatJSONOrNull("[]"));
        assertNull(Jsonx.formatJSONOrNull("null"));
        assertEquals(sourceArrayFormatResult, Jsonx.formatJSONOrNull(sourceArray));
        assertEquals(sourceObjectFromResult, Jsonx.formatJSONOrNull(sourceObject));

        assertEquals("[]", Jsonx.formatToString(new JSONArray()));
        assertEquals(sourceArrayFormatResult, Jsonx.formatToString(new JSONArray(sourceArray)));
        assertNull("[]", Jsonx.formatToStringOrNull((JSONArray) null));
        assertNull("[]", Jsonx.formatToStringOrNull(new JSONArray()));
        assertEquals(sourceArrayFormatResult, Jsonx.formatToStringOrNull(new JSONArray(sourceArray)));

        assertEquals("{}", Jsonx.formatToString(new JSONObject()));
        assertEquals(sourceObjectFromResult, Jsonx.formatToString(new JSONObject(sourceObject)));
        assertNull(Jsonx.formatToStringOrNull((JSONObject) null));
        assertNull(Jsonx.formatToStringOrNull(new JSONObject()));
        assertEquals(sourceObjectFromResult, Jsonx.formatToStringOrNull(new JSONObject(sourceObject)));
    }

    public static class Bean {
        private final String name;
        private final int age;

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
            return age == bean.age && Objects.equals(name, bean.name);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{age, name});
        }
    }

    @NotNull
    @SafeVarargs
    private final <T> T[] arrayOf(@NotNull T... elements) {
        return elements;
    }

    @NotNull
    @SafeVarargs
    private final <T> ArrayList<T> arrayListOf(@NotNull T... elements) {
        if (elements.length > 0) {
            ArrayList<T> list = new ArrayList<>(elements.length);
            Collections.addAll(list, elements);
            return list;
        } else {
            return new ArrayList<>(0);
        }
    }
}
