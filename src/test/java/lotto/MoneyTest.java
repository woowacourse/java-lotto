package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("구매 금액이 숫자 아니면 예외 발생")
    @Test
    void type_exception() {
        assertThatThrownBy(() -> {
            Money.from("2000원");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 숫자로만 입력하세요");
    }

    @DisplayName("구매 금액이 0원 이하이면 예외 발생")
    @Test
    void boundary_exception() {
        assertThatThrownBy(() -> {
            Money.from("0");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 0원보다 커야 합니다");
    }

    @DisplayName("구매 금액이 1000원 단위가 아니면 예외 발생")
    @Test
    void unit_exception() {
        assertThatThrownBy(() -> {
            Money.from("1500");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 1000원 단위로 입력하세요");
    }

    @DisplayName("2000원치 로또가 2장인지 확인한다")
    @Test
    public void purchase_amount_2000() {
        Money money = Money.from("2000");

        assertThat(money.purchase(1000)).isEqualTo(2);
    }

    @DisplayName("3000원치 로또가 3장인지 확인한다")
    @Test
    public void purchase_amount_3000() {
        Money money = Money.from("3000");

        assertThat(money.purchase(1000)).isEqualTo(3);
    }
}
