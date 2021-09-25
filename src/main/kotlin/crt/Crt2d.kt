package crt

import gcd.extGcd
import util.mod

// ref: https://qiita.com/drken/items/ae02240cd1f8edfc86fd

/**
 * calc crt(Chinese Remainder Theorem) with extGcd.
 * this func is limiting problem in 2d.
 */
fun crt2d(b1: Long, m1: Long, b2: Long, m2: Long): Pair<Long, Long> {
    val (p, q, d) = extGcd(m1, m2)
    if ((b2 - b1) % d != 0L) return 0L to -1L
    val m = m1 * (m2 / d)
    // m2/d maybe remove effect from 'm2'
    val tmp = (b2 - b1) / d * p % (m2 / d)
    val r = mod(b1 + m1 * tmp, m)
    return r to m
}