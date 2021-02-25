package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    private static final String MONEY_UNIT_ERROR = "[ERROR] 금액을 1000단위로 입력해주세요";
    private static final String MONEY_INT_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    private static final String MONEY_RANGE_ERROR = "[ERROR] 금액을 1000원 이상 입력해주세요";

    @Test
    @DisplayName("입력된 값이 숫자가 아닐 때 에러 발생")
    void money_isInteger() {
        String falseMoney = "로또값";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(MONEY_INT_ERROR);
    }

    @Test
    @DisplayName("입력된 값이 1000보다 작을 때 에러 발생")
    void money_isMoreThanThousand() {
        String falseMoney = "500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(MONEY_RANGE_ERROR);
    }

    @Test
    @DisplayName("입력된 값이 1000으로 나누어 떨어지지 않을 때 에러 발생")
    void money_noChange() {
        String falseMoney = "1500";
        assertThatThrownBy(() -> {
            new Money(falseMoney);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(MONEY_UNIT_ERROR);
    }
}
