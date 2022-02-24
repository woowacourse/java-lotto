package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static view.LottoNumbersParser.INVALID_LOTTO_NUMBER_FORMAT_MESSAGE;

import java.util.List;
import model.LottoNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumbersParserTest {

    private LottoNumbersParser parser;

    @BeforeEach
    void setUp() {
        parser = new LottoNumbersParser();
    }

    @Test
    @DisplayName("로또 당첨 번호 숫자가 아닌 경우")
    void winningNumberNotNumericValue() {
        assertThatThrownBy(() -> parser.parse("a,b,c,d,e,f"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }

    @Test
    @DisplayName("로또 당첨 번호 분리")
    void splitWinningNumber() {
        assertThat(parser.parse("1,2,3,4,5,6")).isEqualTo(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
    }

    @Test
    @DisplayName("로또 당첨 번호 공백 제거 분리")
    void splitWinningNumberWithTrim() {
        assertThat(parser.parse("1, 2,3,4 ,5,    6")).isEqualTo(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
    }

    @ParameterizedTest(name = "잘못된 길이의 당첨 번호 분리 실패 : 입력값 - {0}")
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void splitWinningNumberUnderSix(String numbers) {
        assertThatThrownBy(() -> parser.parse(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100,200,3,4,5,6", "1,2,3,4,5", "01, 0, 1, 2, 3, 4", "-1, 1, 2, 3, 4, 5", "46, 1, 2, 3, 4, 5"})
    @DisplayName("잘못된 로또 번호 포맷 검증")
    void validateInvalidLottoNumberFormat(String invalidLottoNumbers) {
        assertThatThrownBy(() -> parser.parse(invalidLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
    }
}
