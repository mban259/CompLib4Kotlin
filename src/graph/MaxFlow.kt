package graph

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

typealias C = Int

// v 頂点の数
class MaxFlow(private val v: Int) {

    private val g = Array<ArrayList<Edge>>(v) { arrayListOf() }

    private val lv = Array<Int>(v) { 0 }

    fun addDirectedEdge(from: Int, to: Int, cap: C) {
        addEdge(from, to, cap, 0)
    }

    fun addUndirectedEdge(from: Int, to: Int, cap: C) {
        addEdge(from, to, cap, cap)
    }

    private fun addEdge(f: Int, t: Int, c1: C, c2: C) {
        val a = Edge(t, c1)
        val b = Edge(f, c2)
        Edge.link(a, b)
        g[f].add(a)
        g[t].add(b)
    }

    fun execute(src: Int, sink: Int, f: C = C.MAX_VALUE): C {
        var flow: C = 0
        var tmp = f
        while (tmp > 0) {
            bfs(src)
            if (lv[sink] == 0) return flow
            while (true) {
                val df = dfs(src, sink, tmp)
                if (df <= 0) {
                    break
                }
                flow += df
                tmp -= df
            }
        }

        return flow
    }

    fun bfs(s: Int) {
        for (i in 0 until v) {
            lv[i] = 0
        }
        val q: Queue<Int> = ArrayDeque<Int>()
        lv[s] = 1
        q.add(s)
        while (q.size > 0) {
            val d = q.poll()
            g[d].forEach {
                if (it.cap > 0 && lv[it.to] == 0) {
                    lv[it.to] = lv[d] + 1
                    q.add(it.to)
                }
            }
        }
    }

    private fun dfs(pos: Int, to: Int, f: C): C {
        if (pos == to) return f
        var ret: C = 0
        var tmp = f
        run {
            g[pos].forEach {
                if (it.cap <= 0 || lv[pos] >= lv[it.to]) return@forEach
                val df = dfs(it.to, to, min(tmp, it.cap))
                it.cap -= df
                it.rev!!.cap += df
                ret += df
                tmp -= df
                if (tmp <= 0) return@run
            }
        }
        return ret
    }

    class Edge(val to: Int, var cap: C) {

        companion object {
            fun link(e1: Edge, e2: Edge) {
                e1.rev = e2
                e2.rev = e1
            }
        }

        var rev: Edge? = null
    }
}
