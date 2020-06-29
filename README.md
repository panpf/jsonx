# JSONX

[![Platform][platform_java_icon]][platform_java_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
![SourceCompatibility][source_compatibility_icon]
![TargetCompatibility][target_compatibility_icon]
[![License][license_icon]][license_link]

Extensions to the org.json standard library

## Getting Started

Add the following to your `build.gradle` file

```grovvy
implementation "com.github.panpf:jsonx:$lastVersion"
```

Please replace `$lastVersion` with the latest version: [![Download][version_java_icon]][version_java_link]

Dependencies：
* org.jetbrains:annotations: 13.0

You need to add additional `org.json` dependencies as follows：
```grovvy
implementation "org.json:org.json:2.0"
```
This is because other platforms besides java, such as android, hive, etc., have built-in `org.json` so in order to avoid unnecessary trouble, the default does not depend on `org.json`

### Kotlin Expansion：

Add the following to your `build.gradle` file

```grovvy
implementation "com.github.panpf:jsonx-ktx:$lastVersion"
```

Dependencies：
* org.jetbrains.kotlin:kotlin-stdlib-jdk8: 1.3.72

Please replace `$lastVersion` with the latest version: [![Download][version_kotlin_icon]][version_kotlin_link]

`jsonx-ktx is a jsonx extension on the Kotlin platform that allows you to use jsonx more comfortably on Kotlin`

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
[license_icon]: https://img.shields.io/badge/License-Apache%202-blue.svg
[license_link]: https://www.apache.org/licenses/LICENSE-2.0
[version_java_icon]: https://api.bintray.com/packages/panpf/maven/jsonx/images/download.svg
[version_java_link]:https://bintray.com/panpf/maven/jsonx/_latestVersion
[version_kotlin_icon]: https://api.bintray.com/packages/panpf/maven/jsonx-ktx/images/download.svg
[version_kotlin_link]: https://bintray.com/panpf/maven/jsonx-ktx/_latestVersion
[source_compatibility_icon]: https://img.shields.io/badge/SourceCompatibility-1.8-red.svg
[target_compatibility_icon]: https://img.shields.io/badge/TargetCompatibility-1.8-red.svg
[Jsonx.java]: jsonx/src/main/java/com/github/panpf/jsonx/Jsonx.java
[JsonxTest.java]: jsonx/src/test/java/com/github/panpf/jsonx/test/JsonxTest.java
[Jsonx.kt]: jsonx-ktx/src/main/java/com/github/panpf/jsonx/Jsonx.kt
[JsonxTest.kt]: jsonx-ktx/src/test/java/com/github/panpf/jsonx/test/JsonxTest.kt

[CHANGELOG.md]: CHANGELOG.md
