package lotterymachine.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("투입 금액이 1000원 이하일 시, 에러가 발생한다.")
    void validateValue() {
        assertThatThrownBy(() -> {
            Money money = new Money(500);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 구매는 기본 1000원 이상부터 할 수 있습니다.");
    }

    @Test
    @DisplayName("구매 가능 개수를 조회한다.")
    void getPurchasePossibleCount() {
        Money money = new Money(2000);
        assertThat(money.getPurchasePossibleCount()).isEqualTo(2);
    }
}
