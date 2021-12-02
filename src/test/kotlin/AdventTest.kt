import java.util.stream.Collectors
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AdventOfCode1Test {
    @Test
    fun `can read values`() {
        val values = getResourceAsText("aoc_1.txt")
        assertTrue(values.isNotEmpty())
    }

    @Test
    fun `can count lines` () {
        val depths = getResourceAsText("aoc_1.txt")
            .split("\n")
            .map { x -> x.trim().toInt() }

        val direction = mutableListOf("N/A");

        depths
            .stream()
            .skip(1)
            .collect(Collectors.toList())
            .forEachIndexed { index, depth -> direction.add(if (depths[index] > depth) "decreased" else "increased" ) }

        assertEquals(1475, direction.count { x -> x == "increased" })
    }

    @Test
    fun `can count lines windowed` () {
        val depths = getResourceAsText("aoc_1.txt")
            .split("\n")
            .map { x -> x.trim().toInt() }

        val direction = mutableListOf("N/A");

        val windowedDepths = depths.windowed(2);

        windowedDepths.map { x -> direction.add(if (x[0] > x[1]) "decreased" else "increased" ) }

        assertEquals(1475, direction.count { x -> x == "increased" })
    }

    @Test
    fun `can count example lines` () {
        val depths = getResourceAsText("aoc_1_example.txt")
            .split("\n")
            .map { x -> x.trim().toInt() }

        val direction = mutableListOf("N/A");

        depths
            .stream()
            .skip(1)
            .collect(Collectors.toList())
            .forEachIndexed { index, depth -> direction.add(if (depths[index] > depth) "decreased" else "increased" ) }

        assertEquals(7, direction.count { x -> x == "increased" })
    }

    @Test
    fun `can count lines windowed with destructuring` () {
        val depths = getResourceAsText("aoc_1.txt")
            .split("\n")
            .map { x -> x.trim().toInt() }

        val windowedDepths = depths.windowed(2);
        val increased = windowedDepths.count { (first, second) -> first < second }
        assertEquals(1475, increased)
    }

    private fun getResourceAsText(path: String): String {
        return object {}.javaClass.getResource(path).readText()
    }
}