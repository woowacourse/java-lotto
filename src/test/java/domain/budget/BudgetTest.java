package domain.budget;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BudgetTest {

    @DisplayName("Budget 정상 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"10000", "14000"})
    void Budget_생성_테스트() {
        assertThatCode(() -> new Budget(new BigDecimal(10000)))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 숫자가 아니면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "1.37", " "})
    void Budget_생성_예외_테스트(String value) {
        assertThatThrownBy(() -> new Budget(new BigDecimal(value)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}