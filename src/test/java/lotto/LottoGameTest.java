package lotto;

import lotto.model.Lotto;
import lotto.model.LottoGame;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {

    @Test
    @DisplayName("수익률 계산 확인")
    void calculateYieldTest() {
        LottoGame lottoGame = new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        int money = 14000;
        List<Lotto> lottos = Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 11, 12, 13)));
        float yield = lottoGame.calculateYield(money, lottos);

        assertThat(yield).isCloseTo(0.35f, Percentage.withPercentage(99));
    }
}
