package lotto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DashBoardTest {

    @DisplayName("당첨 내역을 기록할 수 있다.")
    @Test
    void ok() {
        Lotto lotto = new Lotto(generateLottoNumbersInRange(1,6));
        Lotto winningLotto = new Lotto(generateLottoNumbersInRange(1,6));
        LottoNumber bonusNumber = new LottoNumber(7);

        DashBoard dashBoard = new DashBoard();
        dashBoard.recordResult(lotto, winningLotto, bonusNumber);

        assertEquals(1, dashBoard.getRanks().get(Rank.FIRST));
        assertEquals(0, dashBoard.getRanks().get(Rank.SECOND));
        assertEquals(0, dashBoard.getRanks().get(Rank.THIRD));
        assertEquals(0, dashBoard.getRanks().get(Rank.FOURTH));
        assertEquals(0, dashBoard.getRanks().get(Rank.FIFTH));
        assertEquals(0, dashBoard.getRanks().get(Rank.NO_PRIZE));
    }

    private static Set<LottoNumber> generateLottoNumbersInRange(int start, int end) {
        Set<LottoNumber> lottoNumbers = new HashSet<>();
        for (int i = start; i <= end; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        return lottoNumbers;
    }
}
