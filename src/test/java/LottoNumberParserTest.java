import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberParserTest {

    private LottoNumberParser parser;

    @BeforeEach
    void setUp() {
        parser = new LottoNumberParser();
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
                .hasMessage(LottoNumberParser.INVALID_LOTTO_NUMBER_LENGTH_MESSAGE);
    }

    @Test
    @DisplayName("중복된 당첨 번호 분리 실패")
    void failSplitWithDuplicateLottoNumber() {
        assertThatThrownBy(() -> parser.parse("1,2,3,3,4,5"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(LottoNumberParser.DUPLICATED_LOTTO_NUMBER_MESSAGE);
    }

    @ParameterizedTest(name = "잘못된 범위의 당첨 번호 분리 실패 : 입력값 - {0}")
    @DisplayName("잘못된 범위의 당첨 번호 분리 실패")
    @ValueSource(strings = {"1, 2, 3, 4, 5, 46", "0, 1, 2, 3, 4, 5"})
    void failSplitWithInvalidRangeLottoNumber(String numbers) {
        assertThatThrownBy(() -> parser.parse(numbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.");
    }
}
