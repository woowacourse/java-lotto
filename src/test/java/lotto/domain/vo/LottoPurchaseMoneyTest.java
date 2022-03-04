package lotto.domain.vo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoPurchaseMoneyTest {

    @DisplayName("구입 금액을 기반으로 티켓 갯수를 반환한다.")
    @Test
    void checkNormalCase() {
        // given
        LottoPurchaseMoney lottoPurchaseMoney = LottoPurchaseMoney.create(14000);

        // when
        int count = lottoPurchaseMoney.calculate(0);

        // then
        assertThat(count).isEqualTo(14);
    }

    @DisplayName("구입 금액이 1000원 미만인 경우 예외를 던진다.")
    @Test
    void checkLackOfMoney() {
        // given & when & then
        assertThatThrownBy(() -> LottoPurchaseMoney.create(500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("거스름돈이 생긴 경우 가능한 티켓 갯수만 반환한다.")
    @Test
    void checkMaximumPossible() {
        // given
        LottoPurchaseMoney lottoPurchaseMoney = LottoPurchaseMoney.create(14500);

        // when
        int count = lottoPurchaseMoney.calculate(0);

        // then
        assertThat(count).isEqualTo(14);
    }

    @DisplayName("구입금액이 음수인 경우 예외를 던진다.")
    @Test
    void checkNegative() {
        // given & when & then
        assertThatThrownBy(() -> LottoPurchaseMoney.create(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 입력 개수 체크")
    @Test
    void checkManualCount() {
        // given
        LottoPurchaseMoney lottoPurchaseMoney = LottoPurchaseMoney.create(14000);

        // when & then
        assertThatThrownBy(() -> lottoPurchaseMoney.calculate(15))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
