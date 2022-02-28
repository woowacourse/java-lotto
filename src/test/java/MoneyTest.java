import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class MoneyTest {

    @Test
    @DisplayName("생성자에 integer 숫자가 들어가면 성공")
    void ask_money() {
        Money money = new Money(12000);
        assertThat(money.money()).isEqualTo(12000);
    }

    @Test
    @DisplayName("로또 금액이 1000원 미만일 때 오류 발생")
    void lotto_amount() {
        Money money = new Money(999);

        assertThatThrownBy(
            money::convertToAmount
        ).isInstanceOf(
            IllegalArgumentException.class);
    }

}
