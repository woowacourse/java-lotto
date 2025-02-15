package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {

    @Test
    @DisplayName("보너스번호의 범위 외 숫자가 입력되면 예외를 발생시킨다.")
    void validateRange() {
        Assertions.assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 1~45 사이의 수를 입력해야 합니다.");
    }

    @Test
    @DisplayName("보너스번호의 최소값이 입력될 수 있는지 테스트")
    void validateInputMinValue() {
        Assertions.assertThatCode(() -> new BonusNumber(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("보너스번호의 최대값이 입력될 수 있는지 테스트")
    void validateInputMaxValue() {
        Assertions.assertThatCode(() -> new BonusNumber(45))
                .doesNotThrowAnyException();
    }

}
