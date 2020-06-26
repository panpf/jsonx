package me.panpf.jsonxkt

import org.json.JSONArray

class JSONArraySequence<T>(private val jsonArray: JSONArray, private val itemConverter: JSONArray.(index: Int) -> T) : Sequence<T> {
    override fun iterator(): Iterator<T> {
        return JSONArrayIterator(jsonArray, itemConverter)
    }
}

class JSONArrayIterator<T>(private val jsonArray: JSONArray, val itemConverter: JSONArray.(index: Int) -> T) : Iterator<T> {
    private var index = 0;
    private val size = jsonArray.length();

    override fun hasNext(): Boolean {
        return index < size
    }

    override fun next(): T {
        return jsonArray.itemConverter(index++)
    }
}

fun <T> JSONArray.asSequence(itemConverter: JSONArray.(index: Int) -> T): Sequence<T> {
    return JSONArraySequence(this, itemConverter)
}