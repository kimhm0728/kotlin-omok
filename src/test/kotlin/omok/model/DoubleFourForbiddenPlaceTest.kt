package omok.model

import omok.model.rule.StoneForbiddenPlaces
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * docs/4-4 금수 테스트 케이스.png 참조
 */
class DoubleFourForbiddenPlaceTest {
    private lateinit var board: Board
    private lateinit var forbiddenPlace: ForbiddenPlace

    @BeforeEach
    fun setUp() {
        board = Board(
            StoneForbiddenPlaces(
                blackForbiddenPlaces = listOf(DoubleFourForbiddenPlace(), DoubleOpenThreeForbiddenPlace(), OverlineForbiddenPlace()),
                whiteForbiddenPlaces = listOf()
            )
        )
        forbiddenPlace = DoubleFourForbiddenPlace()
    }

    @Test
    fun `4-4 금수 테스트 케이스 A의 경우 돌을 놓을 수 없다`() {
        board.place(Position(3, 2), Stone.BLACK)
        board.place(Position(3, 3), Stone.BLACK)
        board.place(Position(3, 6), Stone.BLACK)
        board.place(Position(3, 8), Stone.BLACK)
        board.place(Position(3, 9), Stone.BLACK)

        val actual = forbiddenPlace.availablePosition(board, Position(3, 5))
        assertThat(actual).isFalse
    }

    @Test
    fun `4-4 금수 테스트 케이스 B의 경우 돌을 놓을 수 없다`() {
        board.place(Position(3, 9), Stone.BLACK)
        board.place(Position(6, 9), Stone.BLACK)
        board.place(Position(7, 9), Stone.BLACK)
        board.place(Position(9, 9), Stone.BLACK)

        val actual = forbiddenPlace.availablePosition(board, Position(5, 9))
        assertThat(actual).isFalse
    }

    @Test
    fun `4-4 금수 테스트 케이스 C의 경우 돌을 놓을 수 없다`() {
        board.place(Position(0, 2), Stone.BLACK)
        board.place(Position(1, 2), Stone.BLACK)
        board.place(Position(3, 2), Stone.BLACK)
        board.place(Position(4, 2), Stone.BLACK)
        board.place(Position(5, 2), Stone.BLACK)

        board.place(Position(9, 4), Stone.BLACK)
        board.place(Position(10, 5), Stone.BLACK)
        board.place(Position(11, 6), Stone.BLACK)

        val actual = forbiddenPlace.availablePosition(board, Position(7, 2))
        assertThat(actual).isFalse
    }

    @Test
    fun `4-4 금수 테스트 케이스 D의 경우 돌을 놓을 수 없다`() {
        board.place(Position(5, 9), Stone.BLACK)
        board.place(Position(6, 9), Stone.BLACK)
        board.place(Position(6, 10), Stone.BLACK)
        board.place(Position(6, 7), Stone.BLACK)
        board.place(Position(7, 7), Stone.BLACK)
        board.place(Position(9, 5), Stone.BLACK)

        val actual = forbiddenPlace.availablePosition(board, Position(6, 8))
        assertThat(actual).isFalse
    }

    @Test
    fun `4-4 금수 테스트 케이스 E의 경우 돌을 놓을 수 없다`() {
        board.place(Position(7, 7), Stone.BLACK)
        board.place(Position(8, 7), Stone.BLACK)
        board.place(Position(9, 7), Stone.BLACK)
        board.place(Position(10, 6), Stone.BLACK)
        board.place(Position(10, 5), Stone.BLACK)
        board.place(Position(10, 4), Stone.BLACK)
        board.place(Position(6, 7), Stone.WHITE)
        board.place(Position(10, 3), Stone.WHITE)

        val actual = forbiddenPlace.availablePosition(board, Position(10, 7))
        assertThat(actual).isFalse
    }
}
