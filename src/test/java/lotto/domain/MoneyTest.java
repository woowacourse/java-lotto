package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MoneyTest {

    @DisplayName("금액이 들어오면 구매할 수 있는 로또의 개수를 반환하는 테스트")
    @Test
    void testCountLotto() {
        Money money = new Money("1000");
        assertThat(money.countLotto()).isEqualTo(1);
    }

    @Test
    @DisplayName("Money 예외사항들 테스트")
    void testValidateMoney() {
        String wrongMoney1 = "";
        String wrongMoney2 = "숫자";
        String wrongMoney3 = null;
        String wrongMoney4 = "999";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(wrongMoney1));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(wrongMoney2));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(wrongMoney3));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(wrongMoney4));
    }
}
