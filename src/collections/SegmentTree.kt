package collections

// Copyright (c) 2020 mban
// Released under the MIT license.
// https://opensource.org/licenses/MIT

// 演算、(左)単位元
class SegmentTree<T>(private val op: (T, T) -> T, private val id: T) {
    companion object {
        // 制約に合うように適宜変えて下さい
        const val size = 1 shl 21
    }

    @Suppress("UNCHECKED_CAST")
    private val array = Array<Any?>(size * 2) { id } as Array<T>

    operator fun get(i: Int) = array[i + size]
    operator fun set(i: Int, n: T) = update(i, n)

    fun update(i: Int, n: T) {
        var index = i + size
        array[index] = n
        while (index > 1) {
            index /= 2
            array[index] = op(array[index * 2], array[index * 2 + 1])
        }
    }

    private fun query(left: Int, right: Int, k: Int, l: Int, r: Int): T {
        if (left <= l && r <= right) {
            return array[k]
        }

        if (r <= left || right <= l) {
            return id
        }

        return op(query(left, right, k * 2, l, (l + r) / 2), query(left, right, k * 2 + 1, (l + r) / 2, r))
    }

    fun query(left: Int, right: Int) = query(left, right, 1, 0, size)
}