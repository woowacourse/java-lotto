package lottogame.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("Money가 정상적으로 생성되었는지 테스트")
    void moneyValueIsValid() {
        assertThat(new Money(1000).getValue()).isEqualTo(1000);
        assertThat(new Money(2000).getValue()).isNotEqualTo(3000);
    }

    @Test
    @DisplayName("Money에 음수가 입력되었을 때 예외처리 진행")
    void moneyValueIsNegativeNumber() {
        assertThatThrownBy(() -> new Money(-1)).isInstanceOf(IllegalArgumentException.class);
    }

}
