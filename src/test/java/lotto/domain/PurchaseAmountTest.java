package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.PurchaseAmount.LOTTO_UNIT_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {
    @DisplayName("구입금액이 1000원으로 나누어 떨어지지 않으면 예외를 던진다")
    @Test
    void 구입금액이_1000원으로_나누어_떨어지지_않으면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 %d원으로 나누어져야 합니다.".formatted(LOTTO_UNIT_PRICE));
    }

    @DisplayName("구입금액이 양수가 아니라면 예외를 던진다")
    @Test
    void 구입금액이_양수가_아니라면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 양수여야 합니다.");
    }

    @DisplayName("구입금액이 10만원을 초과하면 예외를 던진다")
    @Test
    void 구입금액이_10만원을_초과하면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(200_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 최대 100000원까지입니다.");
    }
}
