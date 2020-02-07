package mathematics.geometry

class EuclideanGeometry {
    companion object {
        // 線分abと線分cdが交差するか?
        // http://www5d.biglobe.ne.jp/~tomoya03/shtml/algorithm/Intersection.htm
        // https://qiita.com/ykob/items/ab7f30c43a0ed52d16f2
        fun intersect(
            ax: Double,
            ay: Double,
            bx: Double,
            by: Double,
            cx: Double,
            cy: Double,
            dx: Double,
            dy: Double
        ): Boolean {
            val ta = (cx - dx) * (ay - cy) + (cy - dy) * (cx - ax)
            val tb = (cx - dx) * (by - cy) + (cy - dy) * (cx - bx)
            val tc = (ax - bx) * (cy - ay) + (ay - by) * (ax - cx)
            val td = (ax - bx) * (dy - ay) + (ay - by) * (ax - dx)

            return tc * td < 0 && ta * tb < 0;
        }
    }
}