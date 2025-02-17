package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumberTest {

    @DisplayName("로또 당첨 결과 계산을 성공한다")
    @Test
    void calculateRankByLottoTest() {
        final Lotto userLotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final Lotto userLotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        final Lotto userLotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 11));
        final Lotto userLotto4 = new Lotto(List.of(1, 2, 3, 4, 11, 13));
        final Lotto userLotto5 = new Lotto(List.of(1, 2, 3, 11, 12, 13));
        final WinningNumber winningNumber = new WinningNumber(new Lotto(List.of(1, 2, 3, 4, 5, 7)), new BonusNumber(6));
        final LottoRank lottoRank1 = winningNumber.calculateRankByLotto(userLotto1);
        final LottoRank lottoRank2 = winningNumber.calculateRankByLotto(userLotto2);
        final LottoRank lottoRank3 = winningNumber.calculateRankByLotto(userLotto3);
        final LottoRank lottoRank4 = winningNumber.calculateRankByLotto(userLotto4);
        final LottoRank lottoRank5 = winningNumber.calculateRankByLotto(userLotto5);

        assertAll(
                () -> assertThat(lottoRank1).isEqualTo(LottoRank.RANK_2),
                () -> assertThat(lottoRank2).isEqualTo(LottoRank.RANK_1),
                () -> assertThat(lottoRank3).isEqualTo(LottoRank.RANK_3),
                () -> assertThat(lottoRank4).isEqualTo(LottoRank.RANK_4),
                () -> assertThat(lottoRank5).isEqualTo(LottoRank.RANK_5)
        );
    }
}
