package crt

import gcd.extGcd
import util.mod

// ref: https://qiita.com/drken/items/ae02240cd1f8edfc86fd
// ref: https://qiita.com/R_olldIce/items/3e2c80baa6d5e6f3abe9#3-crt

/**
 * calc crt(Chinese Remainder Theorem) with extGcd.
 */
fun crtNd(b: List<Long>, m: List<Long>): Pair<Long, Long> {
    var r = 0L
    var M = 1L
    b.zip(m).forEach { (bi, mi) ->
        val (p, _, d) = extGcd(M, mi)
        if ((bi - r) % d != 0L) return 0L to -1L
        val tmp = (bi - r) / d * p % (mi / d)
        r += M * tmp
        M *= mi / d
    }
    return mod(r, M) to M
}