package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoGeneratorsParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6 ", "1, 2, 3, 4, 5, 6\n 1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6\n "})
    void 로또_수동_유효성_예외_테스트(String input) {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            LottoNoGeneratorsParser.parse(input);
        }).withMessage("숫자 6개와 쉼표로 구성되어야 합니다.");
    }
}