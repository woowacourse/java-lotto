package parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.parser.BonusNumberParser;

public class BonusNumberParserTest {
    @DisplayName("보너스넘버_중복예외_테스트")
    @Test
    void 보너스넘버_중복예외_테스트() {
        List<Integer> winningNumber = Arrays.asList(1, 2, 3, 4, 5, 6);
        String bonusNumberInput = "6";
        assertThatThrownBy(() -> BonusNumberParser.parseBonusNumber(winningNumber, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호가 당첨 번호와 같습니다.");
    }

    @DisplayName("보너스넘버_범위예외_테스트")
    @Test
    void 보너스넘버_범위예외_테스트() {
        List<Integer> winningNumber = Arrays.asList(1, 2, 3, 4, 5, 6);
        String bonusNumberInput = "46";
        assertThatThrownBy(() -> BonusNumberParser.parseBonusNumber(winningNumber, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 숫자가 1~45의 유효 범위를 벗어납니다.");
    }
}