package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.LottoNumberParser;

public class LottoNumberParserTest {

    private LottoNumberParser parser;

    @BeforeEach
    void setUp() {
        parser = new LottoNumberParser();
    }

    @Test
    @DisplayName("로또 당첨 번호 숫자가 아닌 경우")
    void winningNumberNotNumericValue() {
        assertThatThrownBy(() -> parser.parse("a,b,c,d,e,f"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 반드시 6개의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("로또 당첨 번호 분리")
    void splitWinningNumber() {
        assertThat(parser.parse("1,2,3,4,5,6")).contains(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("로또 당첨 번호 공백 제거 분리")
    void splitWinningNumberWithTrim() {
        assertThat(parser.parse("1, 2,3,4 ,5,    6")).contains(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest(name = "잘못된 길이의 당첨 번호 분리 실패 : 입력값 - {0}")
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void splitWinningNumberUnderSix(String numbers) {
        assertThatThrownBy(() -> parser.parse(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 반드시 6개의 숫자여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"100,200,3,4,5,6", "1,2,3,4,5"})
    @DisplayName("잘못된 로또 번호 포맷 검증")
    void validateInvalidLottoNumberFormat(String invalidLottoNumbers) {
        assertThatThrownBy(() -> parser.parse(invalidLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoNumberParser.INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }
}
