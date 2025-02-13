package model;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    static WinningNumber winningNumber;

    @BeforeAll
    static void setWinningNumber() {
         winningNumber = new WinningNumber("2, 3, 4, 5, 6, 7");
    }

    @DisplayName("보너스 번호를 정상적으로 저장한다")
    @ParameterizedTest
    @CsvSource(value = {"1:1", "15:15", "45:45"}, delimiter = ':')
    void savePurchase(String inputString, int expectedOutput) {
        BonusNumber bonusNumber = new BonusNumber(inputString, winningNumber);
        assertThat(bonusNumber.getNumber()).isEqualTo(expectedOutput);
    }

    @DisplayName("보너스 번호가 일 이상 사십오 이하의 범위를 벗어난 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "46"})
    void outOfNumberRangeInWinningNumber(String bonusNumberInput) {
        assertThatThrownBy(() -> new BonusNumber(bonusNumberInput, winningNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 정수가 아닌 경우 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "@", "6.0", " ", ""})
    void notIntegerLottoNumber(String bonusNumberInput) {
        assertThatThrownBy(() -> new BonusNumber(bonusNumberInput, winningNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외가 발생한다")
    @Test
    void duplicateBonusNumberWithWinningNumber() {
        assertThatThrownBy(() -> new BonusNumber("2", winningNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}