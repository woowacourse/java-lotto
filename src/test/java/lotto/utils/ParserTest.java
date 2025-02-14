package lotto.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParserTest {

    @DisplayName("하나의 숫자를 넣지않으면 예외를 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {"a", "1,2"})
    void 하나의_숫자를_넣지않으면_예외를_발생한다(String number) {

        //when & then
        assertThatThrownBy(() -> Parser.parseToInteger(number))
                .isInstanceOf(LottoException.class)
                .hasMessage("[ERROR] 올바르지 않은 입력값입니다.");
    }

}
