package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    @DisplayName("LottoNumber 생성자는 정수를 입력받아 number를 초기화한다.")
    @Test
    void constructor() {
        assertThatNoException()
                .isThrownBy(() -> LottoNumber.valueOf(30));
    }

    @DisplayName("Number 생성자는 1 ~ 45가 아닌 정수가 들어올 경우 예외가 발생한다.")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {0, 46})
    void constructor_errorOnRange(int input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoNumber.valueOf(input))
                .withMessage("로또 숫자 범위는 1 ~ 45입니다.");
    }
}
