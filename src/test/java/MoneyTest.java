import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class MoneyTest {

    @Test
    @DisplayName("생성자에 integer 숫자가 들어가면 성공")
    void ask_money() {

        assertThatCode(() -> {
            new Money(12000, 3);
        }).doesNotThrowAnyException();

    }

    @Test
    @DisplayName("로또 금액이 1000원 미만일 때 오류 발생")
    void lotto_amount() {

        assertThatThrownBy(() ->
            new Money(999, 1)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]: 금액이 부족해 로또를 구입할 수 없습니다. (로또는 안사는게 이득임.)");
    }

    @Test
    @DisplayName("돈 보다 같은 양의 수동 로또 장 수 들어 올 시 생성 성공")
    void money_isAffordable_success1() {
        Money money = new Money(2000, 2);
        assertThat(money.getManualAmount()).isEqualTo(2);
    }

    @Test
    @DisplayName("돈 보다 적은 양의 수동 로또 장 수 들어 올 시 생성 성공")
    void money_isAffordable_success2() {
        Money money = new Money(2000, 1);
        assertThat(money.getManualAmount()).isEqualTo(1);
    }

    @Test
    @DisplayName("돈 보다 많은 양의 로또 장 수 들어올 시 exception 반환")
    void money_isAffordable_fail() {

        assertThatThrownBy(() ->
            new Money(1000, 2)
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 수동 로또를 구매하기에 입력하신 금액이 너무 적습니다.");
    }

}
