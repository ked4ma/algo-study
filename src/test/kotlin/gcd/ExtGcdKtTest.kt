package gcd

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ExtGcdKtTest {
    @Test
    fun test_withGcd() {
        val (x, y, d) = extGcd(111, 30)
        assertEquals(3, x)
        assertEquals(-11, y)
        assertEquals(3, d)
    }

    @Test
    fun test_coprime() {
        val (x, y, d) = extGcd(11, 3)
        assertEquals(-1, x)
        assertEquals(4, y)
        assertEquals(1, d)
    }
}