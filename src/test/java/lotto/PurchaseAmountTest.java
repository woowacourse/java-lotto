package lotto;

import domain.PurchaseAmount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PurchaseAmountTest {
    @Test
    void 구매금액이_숫자인지_검증(){
        assertThatThrownBy(() -> {
            new PurchaseAmount("돈");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 숫자여야합니다.");
    }

    @Test
    void 구매금액이_음수인지_검증() {
        assertThatThrownBy(() -> {
            new PurchaseAmount("-1");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 음수일 수 없습니다.");
    }

}
