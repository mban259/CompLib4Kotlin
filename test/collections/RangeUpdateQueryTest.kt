package collections

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RangeUpdateQueryTest {
    @Test
    fun abc017DSample1() {
        val st = RangeUpdateQuery<Int>({ l, r -> l + r }, 0)

        st.update(0, 1)
        // 1 0 0 0 0 0
        st.update(1, 3, st[0])
        // 1 1 1 0 0 0
        st.update(2, 4, st[1])
        // 1 1 2 1 0 0
        st.update(3, 5, st[2])
        // 1 1 2 3 2 0

        st.update(4, 5, st[3])
        // 1 1 2 3 5 0
        st.update(5, 6, st[4])
        // 1 1 2 3 5 5
        assertEquals(1, st[0])
        assertEquals(1, st[1])
        assertEquals(2, st[2])
        assertEquals(3, st[3])
        assertEquals(5, st[4])
        assertEquals(5, st[5])
    }

    @Test
    fun abc017DSample2() {
        val st = RangeUpdateQuery<Int>({ l, r -> l + r }, 0)

        st.update(0, 1)
        // 1 0 0 0 0 0 0
        st.update(1, 7, st[0])
        // 1 1 1 1 1 1 1
        st.update(2, 7, st[1])
        // 1 1 2 2 2 2 2
        st.update(3, 7, st[2])
        // 1 1 2 4 4 4 4
        st.update(4, 7, st[3])
        // 1 1 2 4 8 8 8
        st.update(5, 7, st[4])
        // 1 1 2 4 8 16 16
        st.update(6, 7, st[5])
        // 1 1 2 4 8 16 32
        assertEquals(1, st[0])
        assertEquals(1, st[1])
        assertEquals(2, st[2])
        assertEquals(4, st[3])
        assertEquals(8, st[4])
        assertEquals(16, st[5])
        assertEquals(32, st[6])
    }
}