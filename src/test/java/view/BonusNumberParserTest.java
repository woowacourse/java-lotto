package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberParserTest {

    @ParameterizedTest
    @CsvSource({"1, 1", "24, 24", "42, 42"})
    @DisplayName("정상적인 보너스 번호 입력 처리")
    void parse(String text, int actual) {
        BonusNumberParser parser = new BonusNumberParser();
        assertThat(parser.parse(text)).isEqualTo(new LottoNumber(actual));
    }

    @ParameterizedTest
    @DisplayName("비정상적인 보너스 번호 입력 처리")
    @ValueSource(strings = {"99", "00", "05", "100", "0", "46", "-3", "클레이"})
    void invalidParse(String text) {
        BonusNumberParser parser = new BonusNumberParser();
        assertThatThrownBy(() -> parser.parse(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 반드시 두자리 양수여야 합니다.");
    }
}
