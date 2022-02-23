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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("로또 구입 금액 입력시 몇 장의 로또 생성하는지 확인")
    void generateLottosTest() {
        int numberOfLottos = LottoGame.getLottoSize(14000);

        assertThat(numberOfLottos).isEqualTo(14);
    }

    @Test
    @DisplayName("로또 구매 금액이 로또 가격으로 나눠떨어지지 않으면 예외를 던진다")
    void validateUnitPrice() {
        assertThatThrownBy(() -> {
            LottoGame.getLottoSize(14100);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.");
    }
}
