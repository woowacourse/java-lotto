package model;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoWinningNumberTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings ={"","  ","\t","\n"})
    @DisplayName("당첨 번호 입력 공백 검증")
    void validateInputLottoNumberBlank(String numbers) {
        assertThatThrownBy(() -> new LottoWinningNumber(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호 문자열 split 검증")
    void validateSplitInputLottoWinningNumber() {
        List<String> winningNumbers = LottoWinningNumber.split("1, 2, 3, 4");
        assertThat(winningNumbers).isEqualTo(Arrays.asList("1","2","3","4"));
    }

    @ParameterizedTest
    @ValueSource(strings ={"azpi, ++, greeanlawn","1dksl,-1","1, 2, as"})
    @DisplayName("당첨 번호가 숫자가 아닌 경우 검증")
    void validateInputLottoWinningNumberIsInt(String numbers) {
        assertThatThrownBy(() -> new LottoWinningNumber(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"46, 1, 2","0, 45"})
    @DisplayName("당첨 번호가 범위 밖인 경우")
    void validateWinningNumberOutOfRange(String numbers) {
        assertThatThrownBy(() -> new LottoWinningNumber(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
