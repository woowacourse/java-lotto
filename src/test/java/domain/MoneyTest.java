package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("음수인 돈 생성시 예외 처리 테스트")
    public void check_car_name_exception() {
        assertThatThrownBy(() -> new Money(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[Error] 돈은 0보다 커야 합니다.");
    }

}
