package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("로또 당첨 결과 계산을 성공한다")
    @Test
    void calculateRankByLottoTest() {
        final Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final WinningNumber winningNumber = new WinningNumber(new Lotto(List.of(1, 2, 3, 4, 5, 7)), new BonusNumber(6));
        final LottoRank lottoRank = winningNumber.calculateRankByLotto(userLotto);
        assertEquals(LottoRank.RANK_2, lottoRank);
    }
}
