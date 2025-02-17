package converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputConverterTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6 : ,",
            "1, 2, 3, 4, 5, 6 : ,",
    }, delimiter = ':')
    void 입력문자열을_Number_List로_변환한다(String input) {
        //given
        InputConverter converter = new InputConverter();
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        //when
        List<Integer> numbers = converter.convertStringToWinningNumberValue(input);
        //then
        assertThat(numbers).isEqualTo(expected);
    }

    @Test
    void 금액_입력_문자열을_정수값으로_변환한다() {
        //given
        InputConverter converter = new InputConverter();
        //when
        int actual = converter.convertStringToMoneyValue("1000");
        //then
        assertThat(1000).isEqualTo(actual);
    }

    @Test
    void 금액_입력_문자열이_정수가_아닌_경우_예외를_발생시킨다() {
        //given
        InputConverter converter = new InputConverter();
        //when & then
        assertThatThrownBy(() -> converter.convertStringToMoneyValue("aaaa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");
    }

    @Test
    void 보너스번호_입력_문자열을_정수값으로_변환한다() {
        //given
        InputConverter converter = new InputConverter();
        //when
        int actual = converter.convertStringToBonusNumberValue("1");
        //then
        assertThat(1).isEqualTo(actual);
    }

    @Test
    void 보너스번호_입력_문자열이_정수가_아닌_경우_예외를_발생시킨다() {
        //given
        InputConverter converter = new InputConverter();
        //when & then
        assertThatThrownBy(() -> converter.convertStringToBonusNumberValue("aa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");
    }
}
