package fft

import kotlin.math.*

fun dft(arr: Array<Double>): Array<Complex> {
    return dft(arr.map { Complex(it, 0.0) }.toTypedArray())
}

fun dft(arr: Array<Complex>, inverse: Boolean = false): Array<Complex> {
    val n = arr.size
    val h = indexBitSize(n)

    val a = arr.copyOf()

    // swap for butterfly
    repeat(n) { i ->
        var j = 0
        for (k in 0 until h) {
            j = j or ((i shr k and 1) shl (h - 1 - k))
        }
        if (i < j) {
            val tmp = a[i]
            a[i] = a[j]
            a[j] = tmp
        }
    }

    for (i in 0 until h) {
        val b = 1 shl i
        repeat(b) { j ->
            val w: Complex = polar(1.0, (2 * PI) / (2 * b) * j * (if (inverse) 1 else -1))
            for (k in 0 until n step 2 * b) {
                val s = a[j + k]
                val t = a[j + k + b] * w
                a[j + k] = s + t
                a[j + k + b] = s - t
            }
        }
    }
    if (inverse) {
        repeat(n) {
            a[it] = a[it] / n.toDouble()
        }
    }
    return a
}

fun polar(rho: Double, theta: Double): Complex {
    return Complex(cos(theta), sin(theta)) * rho
}

data class Complex(val real: Double, val imaginary: Double) {

    companion object {
        val zero = Complex(0.0, 0.0)
    }

    fun reciprocal(): Complex {
        val scale = (real * real) + (imaginary * imaginary)
        return Complex(real / scale, -imaginary / scale)
    }

    fun abs(): Double = Math.hypot(real, imaginary)

    operator fun unaryMinus(): Complex = Complex(-real, -imaginary)
    operator fun plus(other: Double): Complex = Complex(real + other, imaginary)
    operator fun minus(other: Double): Complex = Complex(real - other, imaginary)
    operator fun times(other: Double): Complex = Complex(real * other, imaginary * other)
    operator fun div(other: Double): Complex = Complex(real / other, imaginary / other)

    operator fun plus(other: Complex): Complex =
        Complex(real + other.real, imaginary + other.imaginary)

    operator fun minus(other: Complex): Complex =
        Complex(real - other.real, imaginary - other.imaginary)

    operator fun times(other: Complex): Complex =
        Complex(
            (real * other.real) - (imaginary * other.imaginary),
            (real * other.imaginary) + (imaginary * other.real)
        )

    operator fun div(other: Complex): Complex = this * other.reciprocal()

}

private fun indexBitSize(n: Int): Int {
    var h = 0
    var i = 0
    while (1 shl i < n) {
        h++
        i++
    }
    return h
}

fun convolve(a: Array<Double>, b: Array<Double>): Array<Double> {
    val s = a.size + b.size - 1
    var t = 1
    while (t < s) {
        t *= 2
    }
    val aa = a.copyOf(t).map { it ?: 0.0 }.toTypedArray()
    val bb = b.copyOf(t).map { it ?: 0.0 }.toTypedArray()
    var A = dft(aa)
    val B = dft(bb)
    repeat(t) {
        A[it] *= B[it]
    }
    A = dft(A, true)
    return A.take(s).map {
        it.real
    }.toTypedArray()
}