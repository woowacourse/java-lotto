package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    public void createDuplicatedNumberBetweenWinningAndBonus() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;
        assertThatThrownBy(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void createWinningNumberWithValidNumbers() {
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        assertDoesNotThrow(() -> {
            new WinningLotto(winningNumbers, bonusNumber);
        });
    }
}
