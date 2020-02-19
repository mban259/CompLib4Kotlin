package mathematics

// Copyright (c) 2020 mban
// Released under the MIT license.
// https://opensource.org/licenses/MIT

typealias Num = Long

class Matrix(private val r: Int, private val c: Int) {
    private val _mat: Array<Array<Num>> = Array(r) { Array<Num>(c) { 0 } }

    operator fun get(i: Int, j: Int) = _mat[i][j]
    operator fun set(i: Int, j: Int, n: Num) {
        _mat[i][j] = n
    }

    val row: Int get() = r
    val col: Int get() = c

    operator fun times(right: Matrix): Matrix {
        assert(c == right.r)
        val result = Matrix(r, right.c)
        for (i in 0 until r) {
            for (j in 0 until right.c) {
                for (k in 0 until c) {
                    result[i, j] += this[i, k] * right[k, j]
                }
            }
        }
        return result
    }

    fun power(n: Long): Matrix {
        assert(r == c)
        var result = Matrix(r, r)
        for (i in 0 until r) {
            result[i, i] = 1
        }
        var tmp = n
        var a = this
        while (tmp > 0) {
            if (tmp % 2 == 1L) {
                result *= a
            }
            tmp /= 2
            a *= a
        }
        return result
    }

    fun power(n: Int): Matrix = power(n.toLong())
}