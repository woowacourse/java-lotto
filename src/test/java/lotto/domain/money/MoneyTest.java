package lotto.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.money.Money.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("정상적으로 생성된다")
    public void createMoney() {
        Money money = new Money("1000");

        assertThat(money).isEqualTo(new Money("1000"));
    }

    @Test
    @DisplayName("구매금액을 기준으로 로또티켓 구매 숫자를 반환")
    public void getLottoCountTest() {
        Money money = new Money("5000");

        assertThat(money.getLottoCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("1000원 미만은 받을 수 없다.")
    public void notEnoughBudgetTest() {
        assertThatThrownBy(() -> {
            new Money("500");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(SHORT_MONEY_MESSAGE, LOTTO_PRICE));
    }

    @Test
    @DisplayName("돈을 생성할 때 문자열을 넘겨주면 예외가 발생한다.")
    public void  invalidNumberFormatTest() {
        assertThatThrownBy(() -> {
            new Money("이건문자열이");
        }).isInstanceOf(NumberFormatException.class)
                .hasMessage(NUMBER_FORMAT_ERROR_MESSAGE);
    }
}
