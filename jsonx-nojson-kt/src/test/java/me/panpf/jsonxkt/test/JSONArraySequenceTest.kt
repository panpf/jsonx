package me.panpf.jsonxkt.test

import me.panpf.jsonxkt.asSequence
import me.panpf.javaxkt.util.requireNotNull
import me.panpf.jsonxkt.toJsonArray
import org.junit.Assert
import org.junit.Test

class JSONArraySequenceTest {
    @Test
    fun test() {
        val jsonString = "[1, 2, 3, 9, 7] "
        val jsonArray = jsonString.toJsonArray().requireNotNull()
        val jsonArraySequence = jsonArray.asSequence { getInt(it) }

        Assert.assertEquals(5, jsonArraySequence.count())
        Assert.assertEquals(1, jsonArraySequence.filter { it % 2 == 0 }.count())
        Assert.assertEquals(4, jsonArraySequence.filter { it % 2 != 0 }.count())
        jsonArraySequence.forEach {
            println("item: $it")
        }
    }
}