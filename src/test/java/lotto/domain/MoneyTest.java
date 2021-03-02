package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("Money 객체 생성 테스트")
    @Test
    public void Money_생성_테스트() {
        Money money = new Money(2000);

        assertThat(money.getMoney()).isEqualTo(2000);
    }

    @DisplayName("예외 처리 : 음수 Money 대한 예외")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1000})
    void Money_음수_예외(int money) {

        assertThatThrownBy(() -> new Money(money))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
