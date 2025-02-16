package converter;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringToNumbersConverterTest {

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
        Assertions.assertThat(numbers).isEqualTo(expected);
    }
}
