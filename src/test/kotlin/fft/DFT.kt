package fft

import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertContentEquals

class DFTTest {
    // https://atcoder.jp/contests/atc001/tasks/fft_c
    @Test
    fun test_withConvolution() {
        val a = arrayOf(0, 1, 2, 3, 4)
        val b = arrayOf(0, 1, 2, 4, 8)
        val ans = convolve(
            a.map { it.toDouble() }.toTypedArray(),
            b.map { it.toDouble() }.toTypedArray(),
        ).map { it.roundToInt() }
        assertContentEquals(ans, listOf(0, 0, 1, 4, 11, 26, 36, 40, 32))
    }
}