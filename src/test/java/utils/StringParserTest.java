package utils;

import domain.BonusNumber;
import domain.WinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringParserTest {
    @DisplayName("문자열로부터 숫자 변환을 정상적으로 성공한다")
    @Test
    void parseToNumberTest(){
        //given
        final String input = "123";
        //when
        int result = StringParser.parseToNumber(input, BonusNumber.INVALID_BONUS_NUMBER_FORMAT);
        //then
        assertEquals(123, result);
    }

    @DisplayName("문자열로부터 숫자 변환을 실패하므로 예외가 발생한다")
    @Test
    void parseToNumberFailTest(){
        //given
        final String input = "aaa";
        //when
        //then
        assertThrows(IllegalArgumentException.class,
                () -> StringParser.parseToNumber(input, BonusNumber.INVALID_BONUS_NUMBER_FORMAT));
    }

    @DisplayName("문자열을 구분자 기준으로 분리하는데 성공한다")
    @Test
    void parseToNumbersTest(){
        //given
        final String input = "1,2,3,4,5,6";
        final String delimiter = ",";
        //when
        List<Integer> result = StringParser.parseToNumbers(input, delimiter, WinningNumber.INVALID_WINNING_NUMBERS_FORMAT);
        //then
        assertThat(result).containsOnly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("문자열을 구분자 기준으로 분리하는데 실패하므로 예외가 발생한다")
    @Test
    void parseToNumbersFailTest(){
        //given
        final String input = "1,2,a,4,5,6";
        final String delimiter = ",";
        //when
        //then
        assertThrows(IllegalArgumentException.class,
                () -> StringParser.parseToNumbers(input, delimiter, BonusNumber.INVALID_BONUS_NUMBER_FORMAT));
    }
}