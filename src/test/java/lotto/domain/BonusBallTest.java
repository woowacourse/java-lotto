package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {

    WinningBalls winningBalls;

    @BeforeEach
    void setUp() {
        winningBalls = new WinningBalls("1,2,3,4,5,6");
    }

    @DisplayName("입력값이 없을 때 예외 발생 확인")
    @NullAndEmptySource
    @ParameterizedTest
    void nullOrBlankTest(String input) {
        assertThatThrownBy(() -> new BonusBall(input, winningBalls))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("보너스 번호를 입력하지 않으셨습니다.");
    }

    @DisplayName("숫자가 아닌 입력값일 때 예외 발생 확인")
    @ParameterizedTest
    @ValueSource(strings = {"3입니다.", "three", "I", "1~"})
    void notNumberInputTest(String input) {
        assertThatThrownBy(() -> new BonusBall(input, winningBalls))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("숫자만 입력하시기 바랍니다.");
    }

    @DisplayName("입력값이 로또 숫자의 범위를 넘었을 때 예외 발생 확인")
    @ParameterizedTest
    @ValueSource(strings = {"0", "46"})
    void exceedRangeInputTest(String input) {
        assertThatThrownBy(() -> new BonusBall(input, winningBalls))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith("이하의 숫자만 가능합니다.");
    }
}
