package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
    @DisplayName("구매 금액이 1000원 단위가 아니면 예외 발생")
    @Test
    void unit_exception() {
        assertThatThrownBy(() -> Lottos.purchaseAuto(Money.from("1500")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 1000원 단위로 입력하세요");
    }
}
