import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SudokuSolverTest {


    @Test
    fun `solve first example`() {
        val actual: SudokuGrid = listOf(
            mutableListOf(X, X, X, X, X, 7, 5, X, 6),
            mutableListOf(4, X, X, X, X, X, 9, 2, X),
            mutableListOf(X, X, X, 2, 1, X, X, X, X),
            mutableListOf(5, X, X, X, X, 8, X, X, X),
            mutableListOf(X, 1, X, X, 9, X, X, 8, X),
            mutableListOf(X, X, X, 6, X, X, X, X, 7),
            mutableListOf(X, X, X, X, 8, 2, X, X, X),
            mutableListOf(X, 7, 4, X, X, X, X, X, 5),
            mutableListOf(9, X, 1, 3, X, X, X, X, X)
        )

        val expected: SudokuGrid = listOf(
            mutableListOf(1, 2, 8, 9, 3, 7, 5, 4, 6),
            mutableListOf(4, 3, 7, 8, 5, 6, 9, 2, 1),
            mutableListOf(6, 5, 9, 2, 1, 4, 3, 7, 8),
            mutableListOf(5, 4, 6, 7, 2, 8, 1, 9, 3),
            mutableListOf(7, 1, 2, 5, 9, 3, 6, 8, 4),
            mutableListOf(8, 9, 3, 6, 4, 1, 2, 5, 7),
            mutableListOf(3, 6, 5, 4, 8, 2, 7, 1, 9),
            mutableListOf(2, 7, 4, 1, 6, 9, 8, 3, 5),
            mutableListOf(9, 8, 1, 3, 7, 5, 4, 6, 2)
        )

        assertEquals(expected, solve(actual))
    }

    @Test
    fun `solve second sudoku`() {
        val actual: SudokuGrid = listOf(
            mutableListOf(5, X, 8, X, 7, X, X, X, X),
            mutableListOf(3, X, 4, X, X, 5, X, X, X),
            mutableListOf(X, 7, X, X, 2, 3, X, 6, X),
            mutableListOf(X, 3, X, X, 5, 6, X, X, X),
            mutableListOf(X, X, 7, X, 4, X, 1, X, X),
            mutableListOf(X, X, X, 9, 3, X, X, 2, X),
            mutableListOf(X, 9, X, 5, 8, X, X, 1, X),
            mutableListOf(X, X, X, X, X, X, 8, X, X),
            mutableListOf(X, X, X, X, 1, X, 7, X, 4)
        )

        val expected: SudokuGrid = listOf(
            mutableListOf(5, 2, 8, 6, 7, 1, 3, 4, 9),
            mutableListOf(3, 6, 4, 8, 9, 5, 2, 7, 1),
            mutableListOf(1, 7, 9, 4, 2, 3, 5, 6, 8),
            mutableListOf(4, 3, 2, 1, 5, 6, 9, 8, 7),
            mutableListOf(9, 5, 7, 2, 4, 8, 1, 3, 6),
            mutableListOf(8, 1, 6, 9, 3, 7, 4, 2, 5),
            mutableListOf(7, 9, 3, 5, 8, 4, 6, 1, 2),
            mutableListOf(2, 4, 1, 7, 6, 9, 8, 5, 3),
            mutableListOf(6, 8, 5, 3, 1, 2, 7, 9, 4)
        )

        assertEquals(expected, solve(actual))
    }

    @Test
    fun `solve third sudoku`() {
        val actual: SudokuGrid = listOf(
            mutableListOf(X, X, 5, X, 3, 9, 8, X, X),
            mutableListOf(X, X, X, 4, X, 1, X, X, X),
            mutableListOf(X, 6, X, X, X, X, X, X, 5),
            mutableListOf(7, X, X, X, X, X, 5, X, 1),
            mutableListOf(X, X, X, X, 2, X, X, X, X),
            mutableListOf(3, X, 2, X, X, X, X, X, 7),
            mutableListOf(6, X, X, X, X, X, X, 3, X),
            mutableListOf(X, X, X, 2, X, 4, X, 5, 8),
            mutableListOf(X, X, 8, 5, 7, X, 9, X, X)
        )

        val expected: SudokuGrid = listOf(
            mutableListOf(1, 2, 5, 6, 3, 9, 8, 7, 4),
            mutableListOf(8, 7, 9, 4, 5, 1, 2, 6, 3),
            mutableListOf(4, 6, 3, 7, 8, 2, 1, 9, 5),
            mutableListOf(7, 8, 4, 3, 9, 6, 5, 2, 1),
            mutableListOf(5, 1, 6, 8, 2, 7, 3, 4, 9),
            mutableListOf(3, 9, 2, 1, 4, 5, 6, 8, 7),
            mutableListOf(6, 5, 7, 9, 1, 8, 4, 3, 2),
            mutableListOf(9, 3, 1, 2, 6, 4, 7, 5, 8),
            mutableListOf(2, 4, 8, 5, 7, 3, 9, 1, 6)
        )

        assertEquals(expected, solve(actual))
    }
}