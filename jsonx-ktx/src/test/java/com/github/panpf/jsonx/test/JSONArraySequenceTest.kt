package com.github.panpf.jsonx.test

import com.github.panpf.jsonx.asSequence
import com.github.panpf.jsonx.toJSONArray
import org.junit.Assert
import org.junit.Test

class JSONArraySequenceTest {
    @Test
    fun test() {
        val jsonString = "[1, 2, 3, 9, 7] "
        val jsonArray = jsonString.toJSONArray()
        val jsonArraySequence = jsonArray.asSequence { getInt(it) }

        Assert.assertEquals(5, jsonArraySequence.count())
        Assert.assertEquals(1, jsonArraySequence.filter { it % 2 == 0 }.count())
        Assert.assertEquals(4, jsonArraySequence.filter { it % 2 != 0 }.count())
        jsonArraySequence.forEach {
            println("item: $it")
        }
    }
}