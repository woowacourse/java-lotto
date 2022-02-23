package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    @ParameterizedTest
    @DisplayName("보너스 볼 번호의 범위가 1~45가 아닌 경우 예외 처리")
    @ValueSource(ints = {-1, 0, 46})
    void validateRangeBonusNumberTest(int bonusNumber) {
        assertThatThrownBy(() -> {
            new BonusNumber(bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 볼 번호가 1~45 범위 내에 해당하지 않습니다.");
    }
}
