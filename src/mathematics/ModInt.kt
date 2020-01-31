package mathematics

class ModInt(_num: Long) {
    companion object {
        const val mod = 1000000007L
    }

    private val num: Long = if (_num < 0) (_num % mod) + mod else _num % mod

    constructor(i: Int) : this(i.toLong())

    operator fun plus(right: ModInt) = ModInt(num + right.toLong())
    operator fun plus(right: Long) = ModInt(num + (right % mod))
    operator fun plus(right: Int) = ModInt(num + (right % mod))

    operator fun minus(right: ModInt) = ModInt(num - right.num)
    operator fun minus(right: Long) = ModInt(num - (right % mod))
    operator fun minus(right: Int) = ModInt(num - (right % mod))

    operator fun times(right: ModInt) = ModInt(num * right.num)
    operator fun times(right: Long) = ModInt(num * (right % mod))
    operator fun times(right: Int) = ModInt(num * (right % mod))

    // modが素数の時のみ
    operator fun div(right: ModInt) = this * right.inverse()
    operator fun div(right: Long) = this / ModInt(right)
    operator fun div(right: Int) = this / ModInt(right)

    fun power(n: Long): ModInt {
        var a = this
        var result = ModInt(1)
        var tmp = n
        while (tmp > 0) {
            if (tmp % 2 == 1L) {
                result *= a
            }
            tmp /= 2
            a *= a
        }
        return result
    }

    // this^nを計算
    fun power(n: Int) = power(n.toLong())

    // modが素数のときのみ 逆元
    fun inverse(): ModInt {
        return power(mod - 2L)
    }

    override fun toString() = num.toString()
    fun toInt() = num.toInt()
    fun toLong() = num
}