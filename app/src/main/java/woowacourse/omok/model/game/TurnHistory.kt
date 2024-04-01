package woowacourse.omok.model.game

import woowacourse.omok.data.OmokEntity
import woowacourse.omok.data.adapter.OmokEntityAdapter
import woowacourse.omok.model.board.Position
import woowacourse.omok.model.board.Stone
import woowacourse.omok.model.player.Player

class TurnHistory(
    private val omokPlayers: OmokPlayers,
    lastOmokEntity: OmokEntity? = null,
) {
    var recentPlayer: Player = omokPlayers.firstOrderPlayer()
        private set

    var recentPosition: Position? = null
        private set

    init {
        lastOmokEntity?.run {
            recentPlayer = if (OmokEntityAdapter.stone(this) == Stone.BLACK) omokPlayers.whiteStonePlayer else omokPlayers.blackStonePlayer
            recentPosition = Position(row, col)
        }
    }

    fun update(position: Position) {
        recentPlayer = omokPlayers.next(recentPlayer)
        recentPosition = position
    }

    fun clear() {
        recentPlayer = omokPlayers.firstOrderPlayer()
        recentPosition = null
    }
}
