package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParserTest {

    @DisplayName("하나의 숫자를 넣지않으면 예외를 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"a", "'1,2'"})
    void 하나의_숫자를_넣지않으면_예외를_발생한다(String number) {

        //when & then
        assertThatThrownBy(() -> Parser.parseToInteger(number))
                .isInstanceOf(LottoException.class)
                .hasMessage("[ERROR] 올바르지 않은 입력값입니다.");
    }

    @DisplayName("올바르게 하나의 숫자를 숫자로 변환한다.")
    @Test
    void 올바르게_하나의_숫자를_숫자로_변환한다() {

        //given
        String number = "5";

        //when && then
        assertThat(Integer.parseInt(number)).isEqualTo(Parser.parseToInteger(number));
    }


    @DisplayName("올바르게 숫자들을 넣으면 숫자 리스트로 변환한다.")
    @Test
    void 올바르게_숫자들을_넣으면_숫자_리스트로_변환한다() {

        //given
        List<String> numbers = List.of("1", "2", "3");

        //when & then
        assertThat(numbers.stream().map(Integer::parseInt).toList())
                .isEqualTo(Parser.parseToIntegers(numbers));
    }
}
