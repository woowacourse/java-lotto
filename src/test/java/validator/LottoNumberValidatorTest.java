package validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberValidatorTest {

    @DisplayName("로또 번호를 잘못 입력 시 IllegalArgumentException 오류를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ada", "46"})
    void checkInvalidInput(String input) {
        assertThatThrownBy(() -> LottoNumberValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}