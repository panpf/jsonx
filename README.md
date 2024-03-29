# JSONX

[![Platform][platform_java_icon]][platform_java_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
![SourceCompatibility][source_compatibility_icon]
![TargetCompatibility][target_compatibility_icon]
![KotlinJvmTarget][kotlin_jvmtarget_icon]
[![License][license_icon]][license_link]

Extensions to the org.json standard library

## Getting Started

This library has been published to `mavenCentral`, Add the following to your `build.gradle` file
```grovvy
implementation "io.github.panpf.jsonx:jsonx:${lastVersion}"
implementation "io.github.panpf.jsonx:jsonx-ktx:${lastVersion}" // Not required. Kotlin extension
implementation "io.json:org.json:2.0"  // Not required. You need it if you are running on the java platform
```

`${lastVersion}`: [![Download][release_icon]][release_link] (no include 'v')

Dependencies：
* org.jetbrains:annotations: 13.0
* org.jetbrains.kotlin:kotlin-stdlib-jdk7: 1.4.10（Only the '\*-ktx' library dependencies it）

## Samples

### Empty judgment：
```java
assertTrue(Jsonx.isEmptyJSON(null));
assertTrue(Jsonx.isEmptyJSON(""));
assertTrue(Jsonx.isEmptyJSON("null"));
assertTrue(Jsonx.isEmptyJSON("{}"));
assertTrue(Jsonx.isEmptyJSON("[]"));

assertFalse(Jsonx.isEmptyJSON("{\"key\":\"value\"}"));
```

### conversion：
```
assertEquals("[\"1\",\"2\",\"3\"]", Jsonx.toJSONArray(new String[]{"1", "2", "3"}).toString());
assertArrayEquals(new String[]{"1", "2", "3"}, Jsonx.toStringArray(new JSONArray("[\"1\",\"2\",\"3\"]")));
```

### get/opt method multiple key support：
```
JSONObject jsonObject = new JSONObject();
jsonObject.put("nick", "Tony");
assertEquals("Tony", Jsonx.getWithKeys(jsonObject, new String[]{"nick", "name"}).toString());

JSONObject jsonObject2 = new JSONObject();
jsonObject2.put("name", "Tony");
assertEquals("Tony", Jsonx.getWithKeys(jsonObject2, new String[]{"nick", "name"}).toString());
```

### format：
```java
String formatted = Jsonx.formatJSON("{\"age\":20,\"name\":\"David\"}")
System.out.println(formatted)
```
out:
```
{
    "age":20,
    "name":"David"
}
```

### JSONArray Sequence support in kotlin
```kotlin
val jsonArray = JSONArray("[1, 2, 3, 4]")
jsonArray.asSequence().forEach{
    println("item: $it")
}
```

### Method List

check:
* boolean isJSON(String)
* boolean isNotJSON(String)
* boolean isJSONObject(String)
* boolean isNotJSONObject(String)
* boolean isJSONArray(String)
* boolean isNotJSONArray(String)
* boolean isEmptyJSON(String)
* boolean isNotEmptyJSON(String)
* boolean isEmptyJSONObject(String)
* boolean isNotEmptyJSONObject(String)
* boolean isEmptyJSONArray(String)
* boolean isNotEmptyJSONArray(String)

toJsonObject:
* JSONObject toJSONObject(String)
* JSONObject toJSONObjectOrNull(String)
* <T> JSONObject toJSONObject(T, ToJSONObject<T>)
* <T> JSONObject toJSONObjectOrNull(T, ToJSONObjectOrNull<T>)

toJsonArray:
* JSONArray toJSONArray(String)
* JSONArray toJSONArrayOrNull(String)
* <T> JSONArray toJSONArray(List<T>, ToJSONObject<T>)
* <T> JSONArray toJSONArrayOrNull(List<T>, ToJSONObjectOrNull<T>)
* <T> JSONArray toJSONArray(List<T>)
* <T> JSONArray toJSONArrayOrNull(List<T>)
* <T> JSONArray toJSONArray(T[] array, ToJSONObject<T>)
* <T> JSONArray toJSONArrayOrNull(T[] array, ToJSONObjectOrNull<T>)
* <T> JSONArray toJSONArray(T[])
* <T> JSONArray toJSONArrayOrNull(T[])
* JSONArray toJSONArray(int[]) 
* JSONArray toJSONArrayOrNull(int[])
* JSONArray toJSONArray(double[]) 
* JSONArray toJSONArrayOrNull(double[])
* JSONArray toJSONArray(long[])
* JSONArray toJSONArrayOrNull(long[])
* JSONArray toJSONArray(boolean[])
* JSONArray toJSONArrayOrNull(boolean[])

toBean:
* <Bean> Bean toBean(JSONObject, ToBean<Bean>)
* <Bean> ArrayList<Bean> toBeanList(JSONArray, ToBean<Bean>)
* <Bean> ArrayList<Bean> toBeanListOrNull(JSONArray, ToBeanOrNull<Bean>)
* <Bean> Bean[] toBeanArray(JSONArray, ToBean<Bean>)
* <Bean> Bean[] toBeanArrayOrNull(JSONArray, ToBeanOrNull<Bean>)
* String[] toStringArray(JSONArray)
* String[] toStringArrayOrNull(JSONArray)
* ArrayList<String> toStringList(JSONArray)
* ArrayList<String> toStringListOrNull(JSONArray)
* int[] toIntArray(JSONArray)
* int[] toIntArrayOrNull(JSONArray)
* double[] toDoubleArray(JSONArray)
* double[] toDoubleArrayOrNull(JSONArray)
* long[] toLongArray(JSONArray)
* long[] toLongArrayOrNull(JSONArray)
* boolean[] toBooleanArray(JSONArray)
* boolean[] toBooleanArrayOrNull(JSONArray)

opt and get:
* Object optWithKeys(JSONObject, String[])
* String optStringWithKeys(JSONObject, String[], String)
* String optStringWithKeys(JSONObject, String[])
* int optIntWithKeys(JSONObject, String[], int)
* int optIntWithKeys(JSONObject, String[])
* long optLongWithKeys(JSONObject, String[], long)
* long optLongWithKeys(JSONObject, String[])
* boolean optBooleanWithKeys(JSONObject, String[], boolean)
* boolean optBooleanWithKeys(JSONObject, String[])
* double optDoubleWithKeys(JSONObject, String[], double)
* double optDoubleWithKeys(JSONObject, String[])
* JSONObject optJSONObjectWithKeys(JSONObject, String[])
* JSONArray optJSONArrayWithKeys(JSONObject, String[])
* Object getWithKeys(JSONObject, String[])
* String getStringWithKeys(JSONObject, String[])
* int getIntWithKeys(JSONObject, String[])
* long getLongWithKeys(JSONObject, String[])
* boolean getBooleanWithKeys(JSONObject, String[])
* double getDoubleWithKeys(JSONObject, String[])
* JSONObject getJSONObjectWithKeys(JSONObject, String[]) 
* JSONArray getJSONArrayWithKeys(JSONObject, String[])

format:
* String formatToString(JSONObject)
* String formatToStringOrNull(JSONObject)
* String formatToString(JSONArray)
* String formatToStringOrNull(JSONArray)
* String formatJSON(String json)
* String formatJSONOrNull(String json)

More features please refer to the source code [Jsonx.java] ([Test][JsonxTest.java])、[Jsonx.kt] ([Test][JsonxTest.kt])

## Change Log

Please view the [CHANGELOG.md] file

## License
    Copyright (C) 2019 Peng fei Pan <panpfpanpf@outlook.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[platform_java_icon]: https://img.shields.io/badge/Platform-Java-red.svg
[platform_java_link]: https://www.java.com
[platform_kotlin_icon]: https://img.shields.io/badge/Platform-Kotlin-blue.svg
[platform_kotlin_link]: http://kotlinlang.org
[source_compatibility_icon]: https://img.shields.io/badge/SourceCompatibility-1.7-red.svg
[target_compatibility_icon]: https://img.shields.io/badge/TargetCompatibility-1.7-red.svg
[kotlin_jvmtarget_icon]: https://img.shields.io/badge/KotlinJvmTarget-1.6-red.svg
[license_icon]: https://img.shields.io/badge/License-Apache%202-blue.svg
[license_link]: https://www.apache.org/licenses/LICENSE-2.0

[release_icon]: https://img.shields.io/maven-central/v/io.github.panpf.jsonx/jsonx
[release_link]: https://repo1.maven.org/maven2/io/github/panpf/jsonx/

[Jsonx.java]: jsonx/src/main/java/com/github/panpf/jsonx/Jsonx.java
[JsonxTest.java]: jsonx/src/test/java/com/github/panpf/jsonx/test/JsonxTest.java
[Jsonx.kt]: jsonx-ktx/src/main/java/com/github/panpf/jsonx/Jsonx.kt
[JsonxTest.kt]: jsonx-ktx/src/test/java/com/github/panpf/jsonx/test/JsonxTest.kt

[CHANGELOG.md]: CHANGELOG.md
