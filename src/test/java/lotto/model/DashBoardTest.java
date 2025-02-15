package lotto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DashBoardTest {

    @DisplayName("당첨 결과를 기록하고 순위별 당첨 횟수를 확인할 수 있다.")
    @Test
    void recordResult() {
        DashBoard dashBoard = new DashBoard();
        dashBoard.recordResult(Rank.FIRST);

        assertEquals(1, dashBoard.getRankCount(Rank.FIRST));
        assertEquals(0, dashBoard.getRankCount(Rank.SECOND));
        assertEquals(0, dashBoard.getRankCount(Rank.THIRD));
        assertEquals(0, dashBoard.getRankCount(Rank.FOURTH));
        assertEquals(0, dashBoard.getRankCount(Rank.FIFTH));
        assertEquals(0, dashBoard.getRankCount(Rank.NO_PRIZE));
    }
}
