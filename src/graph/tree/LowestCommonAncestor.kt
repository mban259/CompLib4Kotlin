package graph.tree

// v ノードの数 辺はv-1個 root 根
class LowestCommonAncestor(private val v: Int, private val root: Int) {
    private val edges = Array(v) { mutableListOf<Int>() }
    fun addEdge(f: Int, t: Int) {
        edges[f].add(t)
        edges[t].add(f)
    }

    private val ancestor = Array(v) { mutableListOf<Int>() }
    private val depth = Array(v) { -1 }

    // 準備
    // iの2^n個上の祖先,iの深さを求める
    fun build() {
        // 現在見てるindex
        var iter = 0

        // stack[iter]の何番目の頂点を見てるか
        val ar = Array(v) { -1 }

        // 通った頂点
        val stack = Array(v) { -1 }
        stack[0] = root
        while (iter >= 0) {
            ar[iter]++
            // 一個上が見てる頂点ならスキップ
            if (iter > 0 && ar[iter] < edges[stack[iter]].size && stack[iter - 1] == edges[stack[iter]][ar[iter]]) {
                ar[iter]++
            }

            if (ar[iter] >= edges[stack[iter]].size) {
                depth[stack[iter]] = iter
                var tmp = 1
                while (iter - tmp >= 0) {
                    ancestor[stack[iter]].add(stack[iter - tmp])
                    tmp *= 2
                }
                ar[iter] = -1
                iter--
            } else {
                stack[iter + 1] = edges[stack[iter]][ar[iter]]
                iter++
            }
        }
    }

    // nの深さ
    fun getDepth(n: Int) = depth[n]

    // 頂点nのi個上の祖先
    fun getAncestor(n: Int, i: Int): Int {
        var pos = n
        var a = 0
        var tmp = i
        while (tmp > 0) {
            if (tmp % 2 == 1) {
                pos = ancestor[pos][a]
            }
            a++
            tmp /= 2
        }
        return pos
    }


    // aとbの最近共通祖先を求める
    fun lowestCommonAncestor(a: Int, b: Int): Int {
        var s = a
        var t = b

        // s,tのdepth
        val depthS = depth[s]
        val depthT = depth[t]

        // 浅い方に高さを合わせる
        if (depthS < depthT) {
            t = getAncestor(t, depthT - depthS)
        } else {
            s = getAncestor(s, depthS - depthT)
        }

        if (s == t) return s

        // s,tの一個上が共通祖先になるようにする
        var i = ancestor[s].size - 1
        while (i >= 0) {
            if (i < ancestor[s].size && ancestor[s][i] != ancestor[t][i]) {
                s = ancestor[s][i]
                t = ancestor[t][i]
            }
            i--
        }

        return ancestor[s][0]
    }
}
