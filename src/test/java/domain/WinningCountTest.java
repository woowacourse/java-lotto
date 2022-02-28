package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("WinningCount 테스트")
public class WinningCountTest {
    @DisplayName("생성자에 0 이상의 숫자를 전달하면 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    void createWinningCountTest(int input) {
        // given & when
        WinningCount WinningCount = new WinningCount(input);

        // then
        assertThat(WinningCount).isNotNull();
    }

    @DisplayName("생성자에 음수를 전달하면 IAE 가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -1})
    void createWinningCountWithNegativeOrZeroShouldFail(int input) {
        assertThatThrownBy(() -> new WinningCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 횟수는 0이상 이어야 합니다.");
    }
}
