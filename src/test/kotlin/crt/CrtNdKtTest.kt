package crt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CrtNdKtTest {
    @Test
    fun test_coprime() {
        val (r, m) = crtNd(
            listOf(2, 3, 23),
            listOf(3, 5, 7)
        )
        assertEquals(23, r)
        assertEquals(105, m)
    }
}