package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
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
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1","asb"})
    @DisplayName("보너스 볼 입력 숫자 검증")
    void validateBonusBallNumber(String number){
        assertThatThrownBy(() -> {
            new BonusBall(number);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
