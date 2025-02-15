package utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import exception.ExceptionMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringParserTest {

    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    @DisplayName("문자열을 숫자 변환하는것을 성공한다")
    @Test
    void parseToNumberTest() {
        //given
        final String number1 = "1";
        final String number2 = "123123";
        //should
        assertAll(
                () -> assertThat(StringParser.parseToNumber(number1, ExceptionMessage.INVALID_MONEY_FORMAT)).isEqualTo(
                        1),
                () -> assertThat(StringParser.parseToNumber(number2, ExceptionMessage.INVALID_MONEY_FORMAT)).isEqualTo(
                        123123)
        );
    }

    @Test
    @DisplayName("문자열을 숫자로 변환하는것을 실패한다")
    void parseToNumberFaliTest() {
        //given
        final String number1 = "1ㅁ";
        final String number2 = "123123ㅁ";
        //should
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(
                                () -> StringParser.parseToNumber(number1, ExceptionMessage.INVALID_BONUS_NUMBER_FORMAT))
                        .withMessageContaining(EXCEPTION_PREFIX),
                () -> assertThatIllegalArgumentException().isThrownBy(
                                () -> StringParser.parseToNumber(number2, ExceptionMessage.INVALID_BONUS_NUMBER_FORMAT))
                        .withMessageContaining(EXCEPTION_PREFIX)
        );
    }

    @DisplayName("문자열을 구분자를 기준으로 숫자 리스트로 변환하는것을 성공한다")
    @Test
    void parseToNumbersTest() {
        //given
        final String delimiter1 = ",";
        final String delimiter2 = ";";
        final String numbers1 = "1,2,3";
        final String numbers2 = "123123;2;3";
        //should
        assertAll(
                () -> assertThat(StringParser.parseToNumbers(numbers1, delimiter1,
                        ExceptionMessage.INVALID_WINNING_NUMBERS_FORMAT)).isEqualTo(
                        List.of(1, 2, 3)),
                () -> assertThat(StringParser.parseToNumbers(numbers2, delimiter2,
                        ExceptionMessage.INVALID_WINNING_NUMBERS_FORMAT)).isEqualTo(List.of(123123, 2, 3))
        );
    }

    @Test
    @DisplayName("문자열을 구분자를 기준으로 숫자 리스트로 변환하는것을 실패한다")
    void parseToNumbersFaliTest() {
        //given
        final String delimiter1 = ",";
        final String delimiter2 = ";";
        final String numbers1 = "1ㅁ,2,3";
        final String numbers2 = "123123;2ㅁ;3";
        //should
        assertAll(
                () -> assertThatIllegalArgumentException().isThrownBy(
                        () -> StringParser.parseToNumbers(numbers1, delimiter1,
                                ExceptionMessage.INVALID_BONUS_NUMBER_FORMAT)).withMessageContaining(EXCEPTION_PREFIX),
                () -> assertThatIllegalArgumentException().isThrownBy(
                        () -> StringParser.parseToNumbers(numbers2, delimiter2,
                                ExceptionMessage.INVALID_BONUS_NUMBER_FORMAT)).withMessageContaining(EXCEPTION_PREFIX)
        );
    }
}
