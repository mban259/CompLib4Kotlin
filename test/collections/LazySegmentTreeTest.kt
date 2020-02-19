// Copyright (c) 2020 mban
// Released under the MIT license.
// https://opensource.org/licenses/MIT

package collections

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class LazySegmentTreeTest {
    @Test
    fun randomTest() {
        // 区間加算、区間和
        val st = LazySegmentTree<Long>({ l, r -> l + r }, 0, { l, r -> l * r }, { l, r -> l + r }, 0)
        val array = Array<Long>(100) { 0 }
        val random = Random(0)
        for (i in 1..1000000) {
            when (random.nextInt()) {
                0 -> {
                    // 区間更新
                    val left = random.nextInt(100)
                    val right = random.nextInt(left, 100) + 1
                    val n = random.nextLong(Int.MAX_VALUE.toLong())
                    for (j in left until right) {
                        array[j] += n
                    }
                    st.update(left, right, n)
                }
                1 -> {
                    // 区間和を求める
                    val left = random.nextInt(100)
                    val right = random.nextInt(left, 100) + 1
                    var expect: Long = 0
                    for (j in left until right) {
                        expect += array[j]
                    }
                    val actual = st.query(left, right)
                    assertEquals(expect, actual)
                }
            }
        }
    }
}