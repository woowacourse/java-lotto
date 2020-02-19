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

    @Test
    void 구매금액이_로또_한_장_가격_보다_낮은지_검증(){
        assertThatThrownBy(() -> {
            new PurchaseAmount("500");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 한장 가격보다 낮은 금액을 입력하셨습니다.");
    }

}
