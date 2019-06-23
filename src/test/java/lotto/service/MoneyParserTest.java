package lotto.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MoneyParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "abc", ","})
    void 로또_개수_예외_테스트(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            MoneyParser.parse(input);
        }).withMessage("금액은 숫자로 입력해야 합니다.");
    }
}