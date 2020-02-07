package collections

// 範囲更新
// op 演算 id 単位元
class RangeUpdateQuery<T>(private val op: (T, T) -> T, private val id: T) {
    companion object {
        const val size = 1 shl 21
    }

    // 区間kはtmp[k]で更新される
    private val tmp = Array<Any?>(size * 2) { id } as Array<T>

    // tmp[k]に値が入っているか?
    private val flag = Array(size * 2) { it >= size }

    private fun eval(k: Int) {
        if (flag[k] && k < size) {
            tmp[k * 2] = op(tmp[k * 2], tmp[k])
            flag[k * 2] = true
            tmp[k * 2 + 1] = op(tmp[k * 2 + 1], tmp[k])
            flag[k * 2 + 1] = true
            tmp[k] = id
            flag[k] = false
        }
    }


    private fun update(left: Int, right: Int, k: Int, l: Int, r: Int, n: T) {
        if (left <= l && r <= right) {
            tmp[k] = op(tmp[k], n)
            flag[k] = true
            return
        }

        if (right <= l || r <= left) {
            return
        }
        eval(k)
        update(left, right, k * 2, l, (l + r) / 2, n)
        update(left, right, k * 2 + 1, (l + r) / 2, r, n)
    }

    private fun search(k: Int) {
        if (k > 0) {
            search(k / 2)
            eval(k)
        }
    }

    operator fun get(i: Int): T {
        search(i + size)
        return tmp[i + size]
    }

    // [l,r)をnで更新
    fun update(left: Int, right: Int, n: T) = update(left, right, 1, 0, size, n)

    // iをnで更新
    fun update(i: Int, n: T) = update(i, i + 1, n)

}