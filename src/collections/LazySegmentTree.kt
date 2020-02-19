package collections

// Copyright (c) 2020 mban
// Released under the MIT license.
// https://opensource.org/licenses/MIT

class LazySegmentTree<T>(
    private val op: (T, T) -> T, // 演算
    private val id: T, // (T,op)の単位元
    private val mul: (T, Int) -> T, //(T,op)のスカラー乗算
    private val u: (T, T) -> T, //区間更新用の演算
    private val uId: T //(T,u)の単位元
) {

    companion object {
        private const val size = 1 shl 21
    }

    private val array: Array<T>
    @Suppress("UNCHECKED_CAST")
    private val tmp = Array<Any?>(size * 2) { uId } as Array<T>
    private val flag = Array(size * 2) { false }

    init {
        @Suppress("UNCHECKED_CAST")
        array = Array<Any?>(size * 2) { uId } as Array<T>
        for (i in size - 1 downTo 1) {
            array[i] = op(array[i * 2], array[i * 2 + 1])
        }
    }

    private fun eval(k: Int, l: Int, r: Int) {
        if (flag[k]) {
            if (r - l > 1) {
                tmp[k * 2] = u(tmp[k * 2], tmp[k])
                flag[k * 2] = true
                tmp[k * 2 + 1] = u(tmp[k * 2 + 1], tmp[k])
                flag[k * 2 + 1] = true
            }
            array[k] = u(array[k], mul(tmp[k], r - l))
            tmp[k] = uId
            flag[k] = false
        }
    }

    private fun update(left: Int, right: Int, k: Int, l: Int, r: Int, n: T) {
        eval(k, l, r)
        if (r <= left || right <= l)
            return
        if (left <= l && r <= right) {
            // 本当はtmp[k] = u(tmp[k],n)だけど上でeval()したのでtmp[k]は単位元になってる
            tmp[k] = n
            flag[k] = true
            // 下のarray[k] = op(array[k * 2], array[k * 2 + 1])のためにarray[k]は正しい値にしなければいけない
            eval(k, l, r)
        } else {
            update(left, right, k * 2, l, (l + r) / 2, n)
            update(left, right, k * 2 + 1, (l + r) / 2, r, n)
            array[k] = op(array[k * 2], array[k * 2 + 1])
        }
    }

    fun update(left: Int, right: Int, n: T) = update(left, right, 1, 0, size, n)

    fun update(i: Int, n: T) = update(i, i + 1, n)

    private fun query(left: Int, right: Int, k: Int, l: Int, r: Int): T {
        eval(k, l, r)
        if (r <= left || right <= l) return id
        if (left <= l && r <= right) return array[k]
        return op(query(left, right, k * 2, l, (l + r) / 2), query(left, right, k * 2 + 1, (l + r) / 2, r))
    }

    fun query(left: Int, right: Int) = query(left, right, 1, 0, size)

    operator fun get(i: Int) = query(i, i + 1)
}