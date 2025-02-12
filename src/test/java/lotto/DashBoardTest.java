package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DashBoardTest {

    @DisplayName("당첨 내역을 기록할 수 있다.")
    @Test
    void ok() {
        Lotto lotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        Lotto winningLotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        DashBoard dashBoard = new DashBoard();
        dashBoard.recordResult(lotto, winningLotto, bonusNumber);

        assertEquals(1, dashBoard.getRanks().get(Rank.FIRST));
        assertEquals(0, dashBoard.getRanks().get(Rank.SECOND));
        assertEquals(0, dashBoard.getRanks().get(Rank.THIRD));
        assertEquals(0, dashBoard.getRanks().get(Rank.FOURTH));
        assertEquals(0, dashBoard.getRanks().get(Rank.FIFTH));
        assertEquals(0, dashBoard.getRanks().get(Rank.NO_PRIZE));
    }
}
