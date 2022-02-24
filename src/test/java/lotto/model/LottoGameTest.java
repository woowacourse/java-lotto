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
        int money = 14000;
        Lottos lottos = new Lottos(Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 11, 12, 13))));
        lottoGame.generateLottoResult(lottos);
        float yield = lottoGame.calculateYield(money);

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

    @Test
    @DisplayName("보너스 볼 번호와 지난주 당첨 번호가 중복된 번호인 경우 예외 처리")
    void validateDuplicateBonusBallNumberTest() {
        assertThatThrownBy(() -> {
            new LottoGame(Arrays.asList(1, 2, 3, 4, 5, 6), 6);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼 번호가 당첨 번호와 중복입니다.");
    }
}
