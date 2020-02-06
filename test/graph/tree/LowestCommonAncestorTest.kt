package graph.tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LowestCommonAncestorTest {

    @Test
    fun abc014DSample1A() {
        val lca = LowestCommonAncestor(5,0)
        lca.addEdge(0,1)
        lca.addEdge(0,2)
        lca.addEdge(0,3)
        lca.addEdge(3,4)

        lca.build()

        assertEquals(0,lca.lowestCommonAncestor(1,2))
        assertEquals(0,lca.lowestCommonAncestor(1,4))
        assertEquals(0,lca.lowestCommonAncestor(1,3))
    }

    @Test
    fun abc014DSample1B() {
        val lca = LowestCommonAncestor(5,4)
        lca.addEdge(0,1)
        lca.addEdge(0,2)
        lca.addEdge(0,3)
        lca.addEdge(3,4)

        lca.build()

        assertEquals(0,lca.lowestCommonAncestor(1,2))
        assertEquals(4,lca.lowestCommonAncestor(1,4))
        assertEquals(3,lca.lowestCommonAncestor(1,3))
    }

    @Test
    fun abc014DSample2() {
        val lca = LowestCommonAncestor(6,0)
        lca.addEdge(0,1)
        lca.addEdge(1,2)
        lca.addEdge(2,3)
        lca.addEdge(3,4)
        lca.addEdge(4,5)

        lca.build()

        assertEquals(0,lca.lowestCommonAncestor(0,2))
        assertEquals(0,lca.lowestCommonAncestor(0,3))
        assertEquals(0,lca.lowestCommonAncestor(0,4))
        assertEquals(0,lca.lowestCommonAncestor(0,5))
    }

    @Test
    fun abc014DSample3() {
        val lca = LowestCommonAncestor(7,0)
        lca.addEdge(2,0)
        lca.addEdge(1,0)
        lca.addEdge(1,3)
        lca.addEdge(1,4)
        lca.addEdge(2,5)
        lca.addEdge(2,6)

        lca.build()

        assertEquals(1,lca.lowestCommonAncestor(3,4))
        assertEquals(0,lca.lowestCommonAncestor(0,5))
        assertEquals(0,lca.lowestCommonAncestor(4,5))
        assertEquals(0,lca.lowestCommonAncestor(3,6))
        assertEquals(0,lca.lowestCommonAncestor(4,2))
    }
}