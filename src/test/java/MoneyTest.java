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
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]: 금액이 부족해 로또를 구입할 수 없습니다. (로또는 안사는게 이득임.)");
    }

    @Test
    @DisplayName("돈 보다 적은 양의 로또 장 수 들어올 시 true 반환")
    void money_isAffordable_true() {
        Money money = new Money(2000);
        assertThat(money.isAffordable(1)).isEqualTo(true);
    }

    @Test
    @DisplayName("돈 보다 많은 양의 로또 장 수 들어올 시 false 반환")
    void money_isAffordable_false() {
        Money money = new Money(2000);
        assertThat(money.isAffordable(3)).isEqualTo(false);
    }

}
