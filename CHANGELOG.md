## new
* improve: Improve Jsonx's isEmptyJSON(String), isNotEmptyJSON(String), isEmptyJSONObject(String), isNotEmptyJSONObject(String), isEmptyJSONArray(String), isNotEmptyJSONArray(String), isJSONObject(String), isNotJSONObject(String), isJSONArray(String), isEmptyJSONArray( String) method performance and memory consumption, especially for long strings, the improvement is obvious
* fix: Jsonx.kt's T.toJSONObject(ToJSONObject<T>) extension function now returns a non-null JSONObject
* new: new add isJSON(String) and isNotJSON(String) method

## 1.0.0-alpha01
* new: Added JSONArray sequence support in kotlin, for example:
```kotlin
val jsonArray = JSONArray("[1, 2, 3, 4]")
jsonArray.asSequence().forEach{
    println("item: $it")
}
```
* upgrade: kotlin stdlib upgrade to 1.3.72
* upgrade: source and target version upgrade to 1.8
* change: jsonx-nojson and jsonx-nojson-kt module package name change to 'com.github.panpf.jsonx'
* change: jsonx-nojson module name change to 'jsonx'
* change: jsonx-nojson-kt module name change to 'jsonx-ktx'
* change: Jsonx fully refactored

## 0.1
first release