package graph

import java.util.*

class Dijkstra(private val v: Int) {
    private val edges = Array(v) { ArrayList<Edge>() }
    private val dist = Array(v) { Long.MAX_VALUE }

    // 有向辺を追加
    fun addDirectedEdge(from: Int, to: Int, cost: Long) {
        edges[from].add(Edge(to, cost))
    }

    // 無向辺を追加
    fun addUndirectedEdge(from: Int, to: Int, cost: Long) {
        addDirectedEdge(from, to, cost)
        addDirectedEdge(to, from, cost)
    }

    // startから各頂点への距離を求める
    fun build(start: Int) {
        val flag = BitSet(v)
        val pq = PriorityQueue<Node>(128) { a, b -> a.cost.compareTo(b.cost) }

        pq.add(Node(start, 0))
        dist[start] = 0

        while (pq.size > 0) {
            val d = pq.poll()!!
            if (flag[d.n]) continue
            flag[d.n] = true
            edges[d.n].forEach {
                if (dist[d.n] + it.cost < dist[it.to]) {
                    dist[it.to] = dist[d.n] + it.cost
                    pq.add(Node(it.to, dist[it.to]))
                }
            }
        }
    }

    // startからiまでの距離
    operator fun get(i: Int) = dist[i]

    data class Node(val n: Int, val cost: Long)
    data class Edge(val to: Int, val cost: Long)
}