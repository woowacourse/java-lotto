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

    @Test
    @DisplayName("당첨 번호 숫자로 변경 및 저장")
    void saveLottoNumber() {
        LottoWinningNumber lottoWinningNumber = new LottoWinningNumber("1, 2, 3");
        List<Integer> winningNumbers = lottoWinningNumber.getWinningNumbers();
        assertThat(winningNumbers).isEqualTo(Arrays.asList(1,2,3));
    }
}
