package service.parser;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberParserTest {
    @DisplayName("보너스넘버_중복예외_테스트")
    @Test
    void 보너스넘버_중복예외_테스트() {
        List<Integer> winningNumber = Arrays.asList(1, 2, 3, 4, 5, 6);
        String bonusNumberInput = "6";
        assertThatThrownBy(() -> BonusNumberParser.parseBonusNumber(winningNumber, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.SAME_NUMBER.getMessage());
    }

    @DisplayName("보너스넘버_범위예외_테스트")
    @Test
    void 보너스넘버_범위예외_테스트() {
        List<Integer> winningNumber = Arrays.asList(1, 2, 3, 4, 5, 6);
        String bonusNumberInput = "46";
        assertThatThrownBy(() -> BonusNumberParser.parseBonusNumber(winningNumber, bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(validator.ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
    }
}