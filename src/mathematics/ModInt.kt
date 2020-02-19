package mathematics

// Copyright (c) 2020 mban
// Released under the MIT license.
// https://opensource.org/licenses/MIT

class ModInt(_num: Number) : Number() {
    companion object {
        const val mod: Long = 1000000007L
    }

    private val num: Long = if (_num.toLong() < 0) (_num.toLong() % mod) + mod else _num.toLong() % mod
    operator fun plus(right: Number) = ModInt(num + (right.toLong() % mod))
    operator fun minus(right: Number) = ModInt(num - (right.toLong() % mod))
    operator fun times(right: Number) = ModInt(num * (right.toLong() % mod))

    // modが素数の時のみ
    operator fun div(right: Number) = this * ModInt(right).inverse()

    fun power(n: Number): ModInt {
        var a = this
        var result = ModInt(1)
        var tmp = n.toLong()
        while (tmp > 0) {
            if (tmp % 2 == 1L) {
                result *= a
            }
            tmp /= 2
            a *= a
        }
        return result
    }

    // modが素数のときのみ 逆元
    fun inverse(): ModInt {
        return power(mod - 2L)
    }

    override fun toString() = num.toString()
    override fun toInt() = num.toInt()
    override fun toLong() = num
    override fun toByte() = num.toByte()
    override fun toChar() = num.toChar()
    override fun toDouble() = num.toDouble()
    override fun toFloat() = num.toFloat()
    override fun toShort() = num.toShort()
}