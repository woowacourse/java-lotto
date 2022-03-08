package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualLottoCountTest {
    private Budget budgetForThree;

    @BeforeEach
    void setUp() {
        budgetForThree = new Budget(3000);
    }

    @Test
    @DisplayName("입력된 숫자형태 문자열을 객체로 파싱")
    void parseManualLottoCount() {
        assertThat(new ManualLottoCount(1, budgetForThree)).isEqualTo(new ManualLottoCount(1, budgetForThree));
    }

    @Test
    @DisplayName("입력값이 음수인 경우 예외")
    void throwNegativeValue() {
        assertThatThrownBy(() -> new ManualLottoCount(-1, budgetForThree))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }
}
