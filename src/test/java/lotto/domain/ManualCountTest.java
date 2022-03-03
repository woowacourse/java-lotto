package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualCountTest {

    @Test
    @DisplayName("수동으로 구입할 횟수가 정상적으로 만들어지는지")
    void Generate_Manual_Count() {
        int inputValue = 5;
        Money money = new Money(10000);
        Count count = new Count(money, inputValue);
        assertThat(count.getCount()).isEqualTo(inputValue);
    }

    @Test
    @DisplayName("입력값이 보유금액보다 비쌀 경우 예외처리")
    void Validate_Short_Money() {
        int inputValue = 100;
        Money money = new Money(10000);

        assertThatThrownBy(() -> new Count(money, inputValue)).hasMessageContaining(Count.ERROR_SHORT_MONEY);
    }
}
