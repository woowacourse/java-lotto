package lotto.model;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoGameTest {

    @Test
    @DisplayName("수익률 계산 확인")
    void calculateYieldTest() {
        LottoGame lottoGame = new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        Money money = new Money(14000);
        Lottos lottos = Lottos.generateManual(Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 11, 12, 13))));
        LottoResult lottoResult = lottoGame.generateLottoResult(lottos);
        float yield = lottoGame.calculateYield(money, lottoResult);

        assertThat(yield).isCloseTo(0.35f, Percentage.withPercentage(99));
    }

    @Test
    @DisplayName("보너스 볼 번호와 지난주 당첨 번호가 중복된 번호인 경우 예외 처리")
    void validateDuplicateBonusBallNumberTest() {
        assertThatThrownBy(() -> {
            new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6), 6);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼 번호가 당첨 번호와 중복입니다.");
    }
}
