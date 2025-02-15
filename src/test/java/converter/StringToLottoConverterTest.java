package converter;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Lotto;
import domain.Number;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringToLottoConverterTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6",
            "1, 2, 3, 4, 5, 6",
    }, delimiter = ':')
    void 입력문자열을_Number_List로_변환한다(String input) {
        //given
        StringToLottoConverter converter = new StringToLottoConverter();
        Set<Number> expected = Set.of(new Number(1), new Number(2), new Number(3), new Number(4), new Number(5),
                new Number(6));
        //when
        Lotto lotto = converter.convert(input);

        //then
        Assertions.assertThat(lotto)
                .extracting("numbers")
                .isEqualTo(expected);
    }

    @Test
    void 당첨번호가_숫자가_아닌_경우_예외를_반환한다() {
        //given
        String input = "1,2,3,4,5,a";
        StringToLottoConverter converter = new StringToLottoConverter();

        //when //then
        assertThatThrownBy(() -> converter.convert(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 입력입니다.");

    }
}
