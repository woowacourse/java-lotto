package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoRankCalculatorTest {

    private LottoRankCalculator lottoRankCalculator;

    @BeforeEach
    void setUp() {
        lottoRankCalculator = new LottoRankCalculator();
    }

    @Test
    void 조건에_알맞은_순위를_반환한다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        LottoRank resultRank = lottoRankCalculator.calculate(lottoNumbers, winningLotto);

        // then
        assertThat(resultRank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    void 보너스_번호에_대한_확인이_요구되는_경우_알맞은_순위를_반환한다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 8));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        LottoRank resultRank = lottoRankCalculator.calculate(lottoNumbers, winningLotto);

        // then
        assertThat(resultRank).isEqualTo(LottoRank.THIRD);
    }
}
