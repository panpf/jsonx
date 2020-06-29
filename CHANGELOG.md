## new
* new: Added JSONArray sequence support in kotlin, for example:
```kotlin
val jsonArray = JSONArray("[1, 2, 3, 4]")
jsonArray.asSequence().forEach{
    println("item: $it")
}
```
* upgrade: kotlin stdlib upgrade to 1.3.72
* upgrade: source and target version upgrade to 1.8

## 0.1
first release