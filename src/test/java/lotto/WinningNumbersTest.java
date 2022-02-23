package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {
    private List<Integer> standardNumbers;

    @BeforeEach
    public void initializeStandardNumbers() {
        standardNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            standardNumbers.add(i);
        }
    }

    @DisplayName("숫자가 7개일 때 예외가 발생한다")
    @Test
    public void size_7_exception() {
        standardNumbers.add(7);
        assertThatThrownBy(() -> {
            new WinningNumbers(standardNumbers, 8);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다");
    }

    @DisplayName("숫자가 5개일 때 예외가 발생한다")
    @Test
    public void size_5_exception() {
        standardNumbers.remove(0);
        assertThatThrownBy(() -> {
            new WinningNumbers(standardNumbers, 6);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다");
    }

    @DisplayName("숫자가 중복될 때 예외가 발생한다")
    @Test
    public void duplicate_exception() {
        final int duplicatedNumber = 5;

        standardNumbers.remove(standardNumbers.indexOf(duplicatedNumber) + 1);
        standardNumbers.add(duplicatedNumber);

        assertThatThrownBy(() -> {
            new WinningNumbers(standardNumbers, 6);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 중복되면 안됩니다");
    }

    @DisplayName("당첨된 번호에 포함된 숫자일 때 true를 반환한다")
    @Test
    public void match_true() {
        WinningNumbers winningNumbers = new WinningNumbers(standardNumbers, 7);
        assertThat(winningNumbers.match(1)).isTrue();
    }

    @DisplayName("당첨된 번호에 포함된 숫자가 아닐 때 false를 반환한다")
    @Test
    public void match_false() {
        WinningNumbers winningNumbers = new WinningNumbers(standardNumbers, 7);
        assertThat(winningNumbers.match(7)).isFalse();
    }
}
