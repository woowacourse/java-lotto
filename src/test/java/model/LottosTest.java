package model;

import static org.assertj.core.api.Assertions.assertThat;
import static util.LottoUtil.generateTestLotto;

import java.util.Arrays;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottosTest {

    private Lottos lottos;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        lottos = new Lottos(Arrays.asList(
                generateTestLotto(1, 2, 3, 6, 7, 8),
                generateTestLotto(10, 11, 12, 4, 5, 6),
                generateTestLotto(1, 2, 3, 4, 10, 11),
                generateTestLotto(1, 2, 3, 4, 5, 10)
        ));
        winningLotto = new WinningLotto(
                generateTestLotto(1, 2, 3, 4, 5, 6),
                new LottoNumber(10)
        );
    }

    @Test
    void 내가_구매한_모든_로또에_대해서_통계_확인한다() {
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        Map<Prize, Integer> result = lottoEvaluator.getResult(lottos);
        assertThat(result.get(Prize._5TH)).isEqualTo(1);
        assertThat(result.get(Prize._4TH)).isEqualTo(2);
        assertThat(result.get(Prize._2ND)).isEqualTo(1);
    }

    @Test
    void 수익률을_계산한다() {
        LottoEvaluator lottoEvaluator = new LottoEvaluator(winningLotto);
        assertThat(lottoEvaluator.computeProfit(lottos)).isEqualTo(7526.25);
    }
}
