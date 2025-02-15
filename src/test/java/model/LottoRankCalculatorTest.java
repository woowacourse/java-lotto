package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoRankCalculatorTest {

    private LottoRankCalculator lottoRankCalculator;
    private WinningLotto validWinningLotto;

    @BeforeEach
    void setUp() {
        lottoRankCalculator = new LottoRankCalculator();
        validWinningLotto = new WinningLotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)), 7);
    }

    @Test
    void 조건에_알맞은_순위를_반환한다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        // when
        LottoRank resultRank = lottoRankCalculator.calculate(lottoNumbers, validWinningLotto);

        // then
        assertThat(resultRank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    void 보너스_번호에_대한_확인이_요구되는_경우_알맞은_순위를_반환한다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 8));

        // when
        LottoRank resultRank = lottoRankCalculator.calculate(lottoNumbers, validWinningLotto);

        // then
        assertThat(resultRank).isEqualTo(LottoRank.THIRD);
    }
}
