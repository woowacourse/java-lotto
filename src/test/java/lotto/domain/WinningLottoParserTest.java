package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6 ", "1, 2, 3, 4, 5, 6,", "1, 2, 3, 4, 5, 6 ", "1, 2, 3"})
    void 당첨_로또_예외_테스트(String input) {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            WinningLottoParser.parse(input, "3");
        }).withMessage("당첨번호는 6개의 숫자와 쉼표로 구성됩니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", ", "})
    void 보너스_번호_예외_테스트(String input) {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            WinningLottoParser.parse("1, 2, 3, 4, 5, 6", input);
        }).withMessage("보너스번호는 숫자로 구성됩니다.");
    }
}