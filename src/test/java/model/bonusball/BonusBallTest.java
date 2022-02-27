package model.bonusball;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import model.exception.BonusBallException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusBallTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "", "\t", "\n"})
    @DisplayName("보너스 볼 입력 공백 검증")
    void validateBonusBallBlank(String number) {
        assertThatThrownBy(() -> {
            new BonusBall(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusBallException.BLANK_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "asb"})
    @DisplayName("보너스 볼 입력 숫자 검증")
    void validateBonusBallNumber(String number) {
        assertThatThrownBy(() -> new BonusBall(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusBallException.NUMBER_ERROR.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"46", "0"})
    @DisplayName("보너스 볼 입력 숫자 범위 검증")
    void validateBonusBallOutOfRange(String number) {
        assertThatThrownBy(() -> new BonusBall(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BonusBallException.RANGE_ERROR.getMessage());
    }

    @Test
    @DisplayName("보너스 볼 숫자로 변환 및 저장")
    void saveBonusBallNumber() {
        BonusBall bonusBall = new BonusBall("7");
        assertThat(bonusBall.getNumber()).isEqualTo(7);
    }
}
