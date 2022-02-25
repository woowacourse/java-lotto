package validator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseMoneyValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"asda", "1700"})
    @DisplayName("구입 금액을 잘못 입력 시 IllegalArgumentException 오류를 발생한다.")
    void checkInvalidInput(String input) {
        assertThatThrownBy(() -> PurchaseMoneyValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
