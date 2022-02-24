package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("로또 구입 금액 입력시 몇 장의 로또 생성하는지 확인")
    void generateLottosTest() {
        Money money = new Money(14000);
        int numberOfLottos = money.getLottoSize();

        assertThat(numberOfLottos).isEqualTo(14);
    }

    @Test
    @DisplayName("로또 구매 금액이 로또 가격으로 나눠떨어지지 않으면 예외를 던진다")
    void validateUnitPrice() {
        assertThatThrownBy(() -> {
            Money money = new Money(14001);
            money.getLottoSize();
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.");
    }
}
