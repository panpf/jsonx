## new
* new: Added JSONArray sequence support in kotlin, for example:
```kotlin
val jsonArray = JSONArray("[1, 2, 3, 4]")
jsonArray.asSequence().forEach{
    println("item: $it")
}
```

## 0.1
first release