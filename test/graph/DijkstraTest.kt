package graph

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DijkstraTest {
    @Test
    fun abc035DSample1() {
        val dijkstra = Dijkstra(2)
        dijkstra.addDirectedEdge(0, 1, 2)
        dijkstra.addDirectedEdge(1, 0, 1)

        dijkstra.build(0)
        assertEquals(0, dijkstra[0])
        assertEquals(2, dijkstra[1])
    }

    @Test
    fun abc035DSample3() {
        val dijkstra = Dijkstra(8)
        dijkstra.addDirectedEdge(0, 7, 1)
        dijkstra.addDirectedEdge(6, 2, 14)
        dijkstra.addDirectedEdge(7, 1, 13)
        dijkstra.addDirectedEdge(2, 4, 4)
        dijkstra.addDirectedEdge(4, 6, 5)
        dijkstra.addDirectedEdge(5, 3, 1)
        dijkstra.addDirectedEdge(5, 7, 17)
        dijkstra.addDirectedEdge(6, 7, 5)
        dijkstra.addDirectedEdge(0, 3, 2)
        dijkstra.addDirectedEdge(3, 6, 1)
        dijkstra.addDirectedEdge(5, 0, 3)
        dijkstra.addDirectedEdge(2, 0, 10)
        dijkstra.addDirectedEdge(1, 5, 5)
        dijkstra.addDirectedEdge(1, 3, 12)
        dijkstra.addDirectedEdge(4, 0, 30)

        dijkstra.build(0)

        assertEquals(0, dijkstra[0])
        assertEquals(14, dijkstra[1])
        assertEquals(17, dijkstra[2])
        assertEquals(2, dijkstra[3])
        assertEquals(21, dijkstra[4])
        assertEquals(19, dijkstra[5])
        assertEquals(3, dijkstra[6])
        assertEquals(1, dijkstra[7])
    }
}