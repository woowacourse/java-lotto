package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @DisplayName("당첨된 번호에 포함된 숫자일 때 true를 반환한다")
    @Test
    public void match_true() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        assertThat(winningNumbers.match(1)).isTrue();
    }

    @DisplayName("당첨된 번호에 포함된 숫자가 아닐 때 false를 반환한다")
    @Test
    public void match_false() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        assertThat(winningNumbers.match(7)).isFalse();
    }
}
