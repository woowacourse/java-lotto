package converter;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.Lotto;
import domain.LottoNumber;
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
        Set<LottoNumber> expected = Set.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5),
                new LottoNumber(6));
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
