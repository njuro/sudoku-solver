data class SudokuField(val row: Int, val column: Int) {
    override fun toString() = "[${row}, ${column}]"
}

fun SudokuField.getBox() = boxes.first { this in it }
const val X = 0 // empty field
val values = (1..9).toSet()


data class SudokuBox(val rows: List<Int>, val columns: List<Int>)

operator fun SudokuBox.contains(field: SudokuField) = field.row in rows && field.column in columns
val boxes = (0..8).chunked(3).flatMap { rows ->
    (0..8).chunked(3).map { columns ->
        SudokuBox(rows, columns)
    }
}


typealias SudokuGrid = List<MutableList<Int>>

operator fun SudokuGrid.set(field: SudokuField, value: Int) = this[field.row].set(field.column, value)
operator fun SudokuGrid.get(field: SudokuField) = this[field.row][field.column]
fun SudokuGrid.getCandidates(field: SudokuField) = getCandidatesFromRow(field)
    .intersect(getCandidatesFromColumn(field))
    .intersect(getCandidatesFromBox(field))

fun SudokuGrid.getCandidatesFromRow(field: SudokuField) = values.minus(get(field.row))
fun SudokuGrid.getCandidatesFromColumn(field: SudokuField) = values.minus(map { it[field.column] })
fun SudokuGrid.getCandidatesFromBox(field: SudokuField) = values.minus(field.getBox().let { block ->
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

fun SudokuGrid.clone() = toList().map { it.toMutableList() }

fun solve(grid: SudokuGrid): SudokuGrid? {
    val possibleMoves = grid.flatMapIndexed { rowIndex, row ->
        row.mapIndexedNotNull { columnIndex, value ->
            SudokuField(
                rowIndex,
                columnIndex
            ).takeIf { value == X }
        }
    }.associateWith { grid.getCandidates(it) }

    if (possibleMoves.isEmpty()) {
        return grid
    }

    val (field, values) = possibleMoves.minByOrNull { it.value.size }!!
    return values.firstNotNullOfOrNull {
        solve(grid.clone().apply { this[field] = it })
    }
}

fun main() {
    val grid: SudokuGrid = listOf(
        mutableListOf(X, 2, X, X, 3, X, 7, 5, 8),
        mutableListOf(4, X, X, 1, X, 7, X, 6, X),
        mutableListOf(X, 3, X, X, 2, X, X, X, X),
        mutableListOf(X, 7, 2, X, X, X, X, X, 4),
        mutableListOf(X, X, X, X, X, X, X, X, X),
        mutableListOf(3, X, X, X, X, X, 5, 7, X),
        mutableListOf(X, X, X, X, 6, X, X, 9, X),
        mutableListOf(X, 8, X, 4, X, 1, X, X, 5),
        mutableListOf(7, 6, 5, X, 9, X, X, 2, X)
    )

    println(
        solve(grid)?.joinToString(separator = "\n") { it.joinToString(separator = "   ") }
            ?: "Unsolvable sudoku"
    )
}