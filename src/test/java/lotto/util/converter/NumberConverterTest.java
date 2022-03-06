package lotto.util.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.util.converter.NumberConverter.convertStringToInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberConverterTest {
    @Test
    @DisplayName("정수 정보만을 가진 문자열 타입 값을 받아 정수 타입으로 반환한다.")
    void convertStringToInt_Test() {
        //given
        final String number = "1";
        final int expected = 1;
        //when
        final int actual = convertStringToInt(number);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("정수 정보만을 가지지 않은 문자열을 정수로 변환하려 하면 예외를 발생시킨다.")
    @ValueSource(strings = {"1.0", "a", "?"})
    void convertStringToInt_exceptionByNotIntegerValue_Test(final String invalidValue) {
        //given
        final String expectedExceptionMessage = "정수를 입력하셔야 합니다.";
        //when then
        assertThatThrownBy(() -> convertStringToInt(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
