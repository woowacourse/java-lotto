package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    Lottos lottos;
    WinningLotto winningLotto;
    LottoResult lottoResult;

    @BeforeEach
    void init() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(2, 3, 4, 5, 6, 7));
        Lotto lotto3 = new Lotto(List.of(3, 4, 5, 6, 7, 8));

        lottos = Lottos.of(Arrays.asList(lotto1, lotto2, lotto3), new Money());
        winningLotto = new WinningLotto(lotto1, new LottoNumber(7));
        lottoResult = new LottoResult(lottos.countLottoRank(winningLotto));
    }

    @Test
    @DisplayName("총 수익 계산")
    void calculate_profit() {
        assertThat(lottoResult.calculateTotalWinningPrize().getMoney()).isEqualTo(2_030_050_000);
    }
}
