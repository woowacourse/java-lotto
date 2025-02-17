package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.constant.ErrorMessage.PURCHASE_AMOUNT_DIVIDE;
import static lotto.constant.ErrorMessage.PURCHASE_AMOUNT_RANGE;
import static lotto.constant.ErrorMessage.PURCHASE_AMOUNT_MAX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {
    @DisplayName("구입금액이 1000원으로 나누어 떨어지지 않으면 예외를 던진다")
    @Test
    void 구입금액이_1000원으로_나누어_떨어지지_않으면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_DIVIDE.getErrorMessage());
    }

    @DisplayName("구입금액이 양수가 아니라면 예외를 던진다")
    @Test
    void 구입금액이_양수가_아니라면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_RANGE.getErrorMessage());
    }

    @DisplayName("구입금액이 10만원을 초과하면 예외를 던진다")
    @Test
    void 구입금액이_10만원을_초과하면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(200_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PURCHASE_AMOUNT_MAX.getErrorMessage());
    }

    @DisplayName("구매한 로또 수량을 계산하여 반환한다")
    @Test
    void 구매한_로또_수량을_계산하여_반환한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);
        assertThat(purchaseAmount.calculateLottoAmount()).isEqualTo(14);
    }
}
