## new
* new: toBean, toString ... now supports direct incoming String

## v1.0.3
* change: ToBeanOrNull.toBean() rename to toBeanOrNull()

## v1.0.2
* change: The parameters of ToBeanOrNull and ToJSONObjectOrNull are now marked as NotNull
* new: Added toBeanArray() and toStringArrayList() methods


## v1.0.1
* downgrade: source and target Compatibility downgrade to 1.7, kotlinOptions.jvmTarget downgrade to 1.6

## v1.0.0

fix:
* fix: Jsonx.kt's T.toJSONObject(ToJSONObject<T>) extension function now returns a non-null JSONObject
* fix: Fix Jsonx's isEmptyJSON(String), isEmptyJSONObject(String), isEmptyJSONArray(String) return true when json string is null or empty string or "null"

upgrade:
* upgrade: kotlin stdlib upgrade to 1.3.72
* upgrade: source and target version upgrade to 1.8

change:
* change: jsonx-nojson and jsonx-nojson-kt module package name change to 'com.github.panpf.jsonx'
* change: jsonx-nojson module name change to 'jsonx'
* change: jsonx-nojson-kt module name change to 'jsonx-ktx'
* change: Jsonx fully refactored

improve:
* improve: Improve Jsonx's isEmptyJSON(String), isNotEmptyJSON(String), isEmptyJSONObject(String), isNotEmptyJSONObject(String), isEmptyJSONArray(String), isNotEmptyJSONArray(String), isJSONObject(String), isNotJSONObject(String), isJSONArray(String), isEmptyJSONArray( String) method performance and memory consumption, especially for long strings, the improvement is obvious

new:
* new: new add isJSON(String) and isNotJSON(String) method
* new: Added JSONArray sequence support in kotlin, for example:
```kotlin
val jsonArray = JSONArray("[1, 2, 3, 4]")
jsonArray.asSequence().forEach{
    println("item: $it")
}
```

## v1.0.0-alpha02
* improve: Improve Jsonx's isEmptyJSON(String), isNotEmptyJSON(String), isEmptyJSONObject(String), isNotEmptyJSONObject(String), isEmptyJSONArray(String), isNotEmptyJSONArray(String), isJSONObject(String), isNotJSONObject(String), isJSONArray(String), isEmptyJSONArray( String) method performance and memory consumption, especially for long strings, the improvement is obvious
* fix: Jsonx.kt's T.toJSONObject(ToJSONObject<T>) extension function now returns a non-null JSONObject
* new: new add isJSON(String) and isNotJSON(String) method
* fix: Fix Jsonx's isEmptyJSON(String), isEmptyJSONObject(String), isEmptyJSONArray(String) return true when json string is null or empty string or "null"

## v1.0.0-alpha01
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

## v0.1
first release