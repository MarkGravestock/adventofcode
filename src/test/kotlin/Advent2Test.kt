import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AdventOfCode2Test {
    @Test
    fun `can count increased values example` () {
        val depths = getResourceAsText("aoc_2_example.txt")

        val increased = calculateIncreased(depths)

        assertEquals(5, increased)
    }

    @Test
    fun `can count increased values` () {
        val depths = getResourceAsText("aoc_2.txt")

        val increased = calculateIncreased(depths)

        assertEquals(1516, increased)
    }

    private fun calculateIncreased(depths: String) = depths
        .split("\n")
        .asSequence()
        .map { x -> x.trim().toInt() }
        .windowed(3)
        .map { x -> x.sum() }
        .windowed(2)
        .count { (first, second) -> first < second }

    private fun getResourceAsText(path: String): String {
        return object {}.javaClass.getResource(path).readText()
    }
}