package collections

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class SegmentTreeTest {
    @Test
    fun randomTest() {
        val st = SegmentTree<Int>({ l, r -> l + r }, 0)
        val array = Array(1000) { 0 }
        val random = Random(0)
        for (i in 1..100000) {
            val type = random.nextInt(2)
            when (type) {
                0 -> {
                    // 更新
                    val index = random.nextInt(1000)
                    val num = random.nextInt(10000)
                    array[index] = num
                    st[index] = num
                }
                1 -> {
                    // 区間演算
                    val left = random.nextInt(1000)
                    val right = random.nextInt(left, 1000) + 1

                    var expect = 0
                    for (j in left until right) {
                        expect += array[j]
                    }
                    val actual = st.query(left, right)

                    assertEquals(expect, actual)
                }
            }
        }
    }

    @Test
    fun test1() {
        val st = SegmentTree({ l, r -> l + r }, 0)
        st[0] = 4
        st[1] = 3
        st[2] = 2
        st[3] = 1
        st[4] = 0
        assertEquals(3, st.query(2, 5))
    }
}