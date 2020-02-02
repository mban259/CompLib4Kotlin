package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaxFlowTest {
    @Test
    fun abc010DSample1() {
        val v = 5
        val minCut = MaxFlow(v)
        minCut.addUndirectedEdge(0, 1, 1)
        minCut.addUndirectedEdge(1, 2, 1)
        minCut.addUndirectedEdge(1, 3, 1)
        minCut.addDirectedEdge(2, 4, 1)
        minCut.addDirectedEdge(3, 4, 1)
        val expect = 1
        val actual = minCut.execute(0, 4)
        assertEquals(expect, actual)
    }

    @Test
    fun abc010DSample2() {
        val v = 5
        val minCut = MaxFlow(v)
        minCut.addUndirectedEdge(0, 1, 1)
        minCut.addUndirectedEdge(0, 2, 1)
        minCut.addUndirectedEdge(1, 3, 1)
        minCut.addDirectedEdge(2, 3, 1)
        minCut.addDirectedEdge(3, 4, 1)
        val expect = 1
        val actual = minCut.execute(0, 4)
        assertEquals(expect, actual)
    }

    @Test
    fun abc010DSample3() {
        val v = 11
        val minCut = MaxFlow(v)
        minCut.addUndirectedEdge(0, 1, 1)
        minCut.addUndirectedEdge(0, 2, 1)
        minCut.addUndirectedEdge(0, 3, 1)
        minCut.addUndirectedEdge(0, 4, 1)
        minCut.addUndirectedEdge(1, 5, 1)
        minCut.addUndirectedEdge(2, 5, 1)
        minCut.addUndirectedEdge(5, 6, 1)
        minCut.addUndirectedEdge(6, 7, 1)
        minCut.addUndirectedEdge(6, 8, 1)
        minCut.addUndirectedEdge(3, 9, 1)
        minCut.addUndirectedEdge(4, 9, 1)
        minCut.addDirectedEdge(7, 10, 1)
        minCut.addDirectedEdge(8, 10, 1)
        minCut.addDirectedEdge(9, 10, 1)
        val expect = 2
        val actual = minCut.execute(0, 10)
        assertEquals(expect, actual)
    }

    @Test
    fun abc010DSample4() {
        val v = 7
        val minCut = MaxFlow(v)
        minCut.addUndirectedEdge(0, 1, 1)
        minCut.addUndirectedEdge(0, 2, 1)
        minCut.addUndirectedEdge(1, 3, 1)
        minCut.addUndirectedEdge(2, 3, 1)
        minCut.addUndirectedEdge(3, 4, 1)
        minCut.addUndirectedEdge(3, 5, 1)
        minCut.addDirectedEdge(4, 6, 1)
        minCut.addDirectedEdge(5, 6, 1)
        val expect = 2
        val actual = minCut.execute(0, 6)
        assertEquals(expect, actual)
    }

    @Test
    fun abc010DSample5() {
        val v = 5
        val minCut = MaxFlow(v)
        minCut.addUndirectedEdge(1, 2, 1)
        minCut.addUndirectedEdge(1, 3, 1)
        minCut.addUndirectedEdge(2, 3, 1)
        minCut.addDirectedEdge(1, 4, 1)
        minCut.addDirectedEdge(2, 4, 1)
        minCut.addDirectedEdge(3, 4, 1)
        val expect = 0
        val actual = minCut.execute(0, 4)
        assertEquals(expect, actual)
    }
}