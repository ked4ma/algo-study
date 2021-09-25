package gcd

// https://qiita.com/drken/items/b97ff231e43bce50199a#3-4-%E6%8B%A1%E5%BC%B5%E3%83%A6%E3%83%BC%E3%82%AF%E3%83%AA%E3%83%83%E3%83%89%E3%81%AE%E4%BA%92%E9%99%A4%E6%B3%95%E3%81%AE%E3%82%A2%E3%83%AB%E3%82%B4%E3%83%AA%E3%82%BA%E3%83%A0%E7%9A%84%E8%A8%98%E8%BF%B0
/**
 * Calc x and y of "a * x + b * y = gcd(a, b)"
 * NOTE: kotlin don't have "call by reference".
 * So, returning x, y with d
 */
fun extGcd(a: Long, b: Long): Triple<Long, Long, Long> {
    fun logic(a: Long, b: Long, x: Long = 0L, y: Long = 0L): Triple<Long, Long, Long> {
        if (b == 0L) {
            return Triple(1L, 0L, a)
        }
        val (ny, nx, d) = logic(b, a % b, y, x)
        return Triple(nx, ny - ((a / b) * nx), d)
    }
    return logic(a, b)
}