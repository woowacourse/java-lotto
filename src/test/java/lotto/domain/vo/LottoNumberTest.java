package lotto.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    @DisplayName("1 ~ 45 숫자로 LottoNumber 객체를 생성한다")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {1, 45})
    void constructor_test(int input) {
        assertThatNoException()
                .isThrownBy(() -> LottoNumber.valueOf(input));
    }

    @DisplayName("LottoNumber 객체는 캐싱하여 사용한다")
    @Test
    void value_cache_test() {
        LottoNumber number1 = LottoNumber.valueOf(1);
        LottoNumber number2 = LottoNumber.valueOf(1);
        LottoNumber number3 = LottoNumber.valueOf(2);

        assertThat(number1 == number2).isTrue();
        assertThat(number1 == number3).isFalse();
        assertThat(number2 == number3).isFalse();
    }

    @DisplayName("1 ~ 45 외 숫자 입력 시 IllegalArgumentException 예외를 발생시킨다")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @ValueSource(ints = {0, 46})
    void constructor_range_exception_test(int input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> LottoNumber.valueOf(input))
                .withMessage("로또 숫자 범위는 1 ~ 45입니다.");
    }
}
