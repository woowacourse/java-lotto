package converter;

import domain.Number;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringToNumbersConverterTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6 : ,",
            "1, 2, 3, 4, 5, 6 : ,",
            "1! 2! 3! 4! 5! 6 : !"
    }, delimiter = ':')
    void 입력문자열을_Number_List로_변환한다(String input, String delimiter) {
        //given
        StringToNumbersConverter converter = new StringToNumbersConverter();
        List<Number> expected = List.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5),
                new Number(6));
        //when
        List<Number> numbers = converter.convert(input, delimiter);
        //then
        Assertions.assertThat(numbers).isEqualTo(expected);
    }

}