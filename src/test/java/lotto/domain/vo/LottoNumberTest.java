package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    @DisplayName("Number 생성자 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {1, 45})
    void constructor_test(int input) {
        assertThatNoException()
                .isThrownBy(() -> new LottoNumber(input));
    }

    @DisplayName("Number 생성자 범위 예외 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {0, 46})
    void constructor_range_exception_test(int input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoNumber(input))
                .withMessage("로또 숫자 범위는 1 ~ 45입니다.");
    }
}
