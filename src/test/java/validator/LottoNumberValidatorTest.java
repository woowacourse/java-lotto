package validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberValidatorTest {
    private static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1~45 사이로 입력해주세요.";

    @DisplayName("로또 번호의 범위가 맞지 않을 시 IllegalArgumentException 오류를 발생한다.")
    @ParameterizedTest(name = "{index}: ''{0}'' 입력")
    @ValueSource(ints = {46, -1, 0})
    void checkNotInRangeInput(int lottoNumber) {
        assertThatThrownBy(() -> LottoNumberValidator.validate(lottoNumber))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
    }
}