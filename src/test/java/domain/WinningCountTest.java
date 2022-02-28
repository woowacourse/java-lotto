package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningCountTest {
    @ParameterizedTest(name = "생성자에 숫자를 전달하면 WinningCount 가 생성")
    @ValueSource(ints = {1, 3, 5})
    void createWinningCountTest(int input) {
        // given & when
        WinningCount WinningCount = new WinningCount(input);

        // then
        assertThat(WinningCount).isNotNull();
    }

    @ParameterizedTest(name = "생성자에 음수를 전달하면 IAE 발생")
    @ValueSource(ints = {-10, -1})
    void createWinningCountWithNegativeOrZeroShouldFail(int input) {
        assertThatThrownBy(() -> new WinningCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 횟수는 0이상 이어야 합니다.");
    }
}
