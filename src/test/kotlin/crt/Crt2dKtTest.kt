package crt

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Crt2dKtTest {
    @Test
    fun test_coprime() {
        val (r, m) = crt2d(2, 3, 3, 5)
        assertEquals(8, r)
        assertEquals(15, m)
    }

    @Test
    fun test_invalid() {
        val (r, m) = crt2d(4, 6, 1, 8)
        assertEquals(0, r)
        assertEquals(-1, m)
    }
}