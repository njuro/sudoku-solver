data class SudokuField(val row: Int, val column: Int) {
    override fun toString() = "[${row}, ${column}]"
}

fun SudokuField.getBlock() = blocks.first { this in it }
const val X = 0 // empty field
val values = (1..9).toSet()


data class SudokuBlock(val rows: IntRange, val columns: IntRange)

operator fun SudokuBlock.contains(field: SudokuField) = field.row in rows && field.column in columns
val blocks = setOf(
    SudokuBlock(rows = (0..2), columns = (0..2)),
    SudokuBlock(rows = (3..5), columns = (0..2)),
    SudokuBlock(rows = (6..8), columns = (0..2)),
    SudokuBlock(rows = (0..2), columns = (3..5)),
    SudokuBlock(rows = (3..5), columns = (3..5)),
    SudokuBlock(rows = (6..8), columns = (3..5)),
    SudokuBlock(rows = (0..2), columns = (6..8)),
    SudokuBlock(rows = (3..5), columns = (6..8)),
    SudokuBlock(rows = (6..8), columns = (6..8)),
)


typealias SudokuGrid = List<List<Int>>

operator fun SudokuGrid.get(field: SudokuField) = get(field.row)[field.column]
fun SudokuGrid.getCandidates(field: SudokuField) = getCandidatesFromRow(field)
    .intersect(getCandidatesFromColumn(field))
    .intersect(getCandidatesFromBlock(field))

fun SudokuGrid.getCandidatesFromRow(field: SudokuField) = values.minus(get(field.row))
fun SudokuGrid.getCandidatesFromColumn(field: SudokuField) = values.minus(map { it[field.column] })
fun SudokuGrid.getCandidatesFromBlock(field: SudokuField) = values.minus(field.getBlock().let { block ->
    flatMapIndexed { rowIndex, row ->
        row.mapIndexedNotNull { columnIndex, value ->
            value.takeIf {
                SudokuField(
                    rowIndex,
                    columnIndex
                ) in block
            }
        }
    }
})

fun solve(grid: SudokuGrid): SudokuGrid {
    val possibleTurns = mutableMapOf<SudokuField, Set<Int>>()

    for (row in 0..8) {
        for (column in 0..8) {
            val field = SudokuField(row, column)
            if (grid[field] == X) {
                possibleTurns[field] = grid.getCandidates(field)
            }
        }
    }

    return grid
}

fun main() {
    val grid: SudokuGrid = listOf(
        listOf(X, 2, X, X, 3, X, 7, 5, 8),
        listOf(4, X, X, 1, X, 7, X, 6, X),
        listOf(X, 3, X, X, 2, X, X, X, X),
        listOf(X, 7, 2, X, X, X, X, X, 4),
        listOf(X, X, X, X, X, X, X, X, X),
        listOf(3, X, X, X, X, X, 5, 7, X),
        listOf(X, X, X, X, 6, X, X, 9, X),
        listOf(X, 8, X, 4, X, 1, X, X, 5),
        listOf(7, 6, 5, X, 9, X, X, 2, X)
    )

    solve(grid)
}