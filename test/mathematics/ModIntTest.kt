package mathematics

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class ModIntTest {
    private val random: Random = Random()

    companion object {
        const val mod: Long = 1000000007L
    }

    @Test
    fun randomTestPlus() {
        for (k in 1..10000) {
            val a: Long = random.nextInt().toLong()
            val b: Long = random.nextInt().toLong()

            var expect = (a + b) % mod
            if (expect < 0) expect += mod

            val actual = (ModInt(a) + ModInt(b)).toLong()

            assertEquals(expect, actual)
        }
    }

    @Test
    fun randomTestMinus() {
        for (k in 1..10000) {
            val a: Long = random.nextInt().toLong()
            val b: Long = random.nextInt().toLong()

            var expect = (a - b) % mod
            if (expect < 0) expect += mod

            val actual = (ModInt(a) - ModInt(b)).toLong()

            assertEquals(expect, actual)
        }
    }

    @Test
    fun randomTestTimes() {
        for (k in 1..10000) {
            val a: Long = random.nextInt().toLong()
            val b: Long = random.nextInt().toLong()

            var expect = (a * b) % mod
            if (expect < 0) expect += mod

            val actual = (ModInt(a) * ModInt(b)).toLong()

            assertEquals(expect, actual)
        }
    }

    @Test
    fun div() {
        // a / b = c
        // a = b * c
        for (t in 1..10000) {
            val a = random.nextInt().toLong()
            val b = random.nextInt().toLong()

            val c = ModInt(a) / ModInt(b)

            val bc = ModInt(b) * c

            assertEquals(ModInt(a).toLong(), bc.toLong())
        }
    }

    @Test
    fun power() {
        for(t in 1..10000){
            val a = random.nextInt()
            val b = random.nextInt(10000)
            var expect = 1L
            for(i in 0 until b){
                expect *= a
                expect %= mod
                if(expect < 0) expect += mod
            }

            val actual = ModInt(a).power(b).toLong()

            assertEquals(expect,actual)
        }
    }

    @Test
    fun randomTestInverse() {
        for (k in 1..10000) {
            val n = random.nextInt().toLong()
            val inv = ModInt(n).inverse()

            assertEquals(1L, (inv * ModInt(n)).toLong())
        }
    }
}