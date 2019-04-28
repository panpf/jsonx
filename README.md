# JSONX

[![Platform][platform_java_icon]][platform_java_link]
[![Platform][platform_kotlin_icon]][platform_kotlin_link]
![SourceCompatibility][source_compatibility_icon]
![TargetCompatibility][target_compatibility_icon]
[![License][license_icon]][license_link]

Extensions to the org.json standard library

### :warning: `Warning`

`Currently in the development stage, some APIs have not been tested yet, please use them carefully`

## Getting Started

Add the following to your `build.gradle` file

```grovvy
compile "me.panpf:jsonx-nojson:$lastVersion"
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
compile "me.panpf:jsonx-nojson-kt:$lastVersion"
```

Dependencies：
* org.jetbrains.kotlin:kotlin-stdlib-jdk7: 1.3.21

Please replace `$lastVersion` with the latest version: [![Download][version_kotlin_icon]][version_kotlin_link]

`jsonx-nojson-kt is a jsonx-nojson extension on the Kotlin platform that allows you to use jsonx-nojson more comfortably on Kotlin`

## Samples

Empty judgment：
```java
assertTrue(Jsonx.isEmpty(""));
assertFalse(Jsonx.isEmpty("{\"key\":\"value\"}"));
```

conversion：
```
assertEquals("[\"1\",\"2\",\"3\"]", Jsonx.toJsonArrayString(new String[]{"1", "2", "3"}));
assertArrayEquals(new String[]{"1", "2", "3"}, Jsonx.toStringArray("[\"1\",\"2\",\"3\"]"));
```

get/opt method multiple key support：
```
JSONObject jsonObject = new JSONObject();
jsonObject.put("age1", 11);
jsonObject.put("name1", "name1");
jsonObject.put("long1", 21L);
jsonObject.put("boolean1", true);
jsonObject.put("double1", 21.0);

assertEquals("Tony", Jsonx.get(jsonObject, new String[]{"age", "年龄", "name1"}).toString());
```

format：
```java
assertEquals("{
    \"name\":\"David\",
    \"age\":20
}", Jsonx.format("{\"age\":20,\"name\":\"David\"}");
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
[version_kotlin_icon]: https://api.bintray.com/packages/panpf/maven/jsonx-kt/images/download.svg
[version_kotlin_link]: https://bintray.com/panpf/maven/jsonx-kt/_latestVersion
[source_compatibility_icon]: https://img.shields.io/badge/SourceCompatibility-1.7-red.svg
[target_compatibility_icon]: https://img.shields.io/badge/TargetCompatibility-1.7-red.svg
[Jsonx.java]: jsonx-nojson/src/main/java/me/panpf/jsonx/Jsonx.java
[JsonxTest.java]: jsonx-nojson/src/test/java/me/panpf/jsonx/test/JsonxTest.java
[Jsonx.kt]: jsonx-nojson-kt/src/main/java/me/panpf/jsonxkt/Jsonx.kt
[JsonxTest.kt]: jsonx-nojson-kt/src/test/java/me/panpf/jsonxkt/test/JsonxTest.kt

[CHANGELOG.md]: CHANGELOG.md
