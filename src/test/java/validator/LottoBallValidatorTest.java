package validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoBallValidatorTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 47})
    void 숫자_범위_에러_처리(int input) {
        assertThatThrownBy(() -> LottoBallValidator.validate(input))
                .isInstanceOf(Exception.class);
    }

}