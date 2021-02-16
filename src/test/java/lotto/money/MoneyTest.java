package lotto.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("돈 객체 생성")
    void moneyCreate() {
        Money money = new Money("10000");
        assertThat(money).isEqualTo(new Money("10000"));
    }

    @Test
    @DisplayName("숫자가 아닌 경우 예외")
    void moneyNotNumber() {
        assertThatThrownBy(() ->
            new Money("*1223")
        ).isInstanceOf(NumberFormatException.class);
    }
}
