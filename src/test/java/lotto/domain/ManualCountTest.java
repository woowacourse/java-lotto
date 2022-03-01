package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualCountTest {

    @Test
    @DisplayName("수동으로 구입할 횟수가 정상적으로 만들어지는지")
    void Generate_Manual_Count() {
        String input = "5";
        Money money = new Money("10000");
        Count count = new Count(money, input);
        assertThat(count.getCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("입력값이 숫자가 아닐 떄 예외처리")
    void Validate_Not_Integer() {
        String input = "abc";
        Money money = new Money("10000");

        assertThatThrownBy(() -> new Count(money, input)).hasMessageContaining(Count.ERROR_NOT_INTEGER);
    }

    @Test
    @DisplayName("입력값이 보유금액보다 비쌀 경우 예외처리")
    void Validate_Short_Money() {
        String input = "100";
        Money money = new Money("10000");

        assertThatThrownBy(() -> new Count(money, input)).hasMessageContaining(Count.ERROR_SHORT_MONEY);
    }
}
