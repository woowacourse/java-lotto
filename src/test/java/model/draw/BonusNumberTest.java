package model.draw;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    static WinningNumber winningNumberFromTwoToSeven;

    @BeforeAll
    static void setWinningNumber() {
        winningNumberFromTwoToSeven = new WinningNumber(new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7)));
    }

    @DisplayName("보너스 번호를 정상적으로 저장한다")
    @ParameterizedTest
    @CsvSource(value = {"1:1", "15:15", "45:45"}, delimiter = ':')
    void savePurchase(int numberInput, int expectedOutput) {
        BonusNumber bonusNumber = new BonusNumber(numberInput, winningNumberFromTwoToSeven);

        assertThat(bonusNumber.getNumber()).isEqualTo(expectedOutput);
    }

    @DisplayName("보너스 번호가 일 이상 사십오 이하의 범위를 벗어난 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    void outOfNumberRangeInWinningNumber(int numberInput) {
        assertThatThrownBy(() -> new BonusNumber(numberInput, winningNumberFromTwoToSeven))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외가 발생한다")
    @Test
    void duplicateBonusNumberWithWinningNumber() {
        assertThatThrownBy(() -> new BonusNumber(2, winningNumberFromTwoToSeven))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
