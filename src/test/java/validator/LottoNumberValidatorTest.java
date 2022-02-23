package validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberValidatorTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 47})
    void 숫자_범위_에러_처리(int input) {
        assertThatThrownBy(() -> LottoNumberValidator.isRightNumberRange(input))
                .isInstanceOf(Exception.class);
    }

}