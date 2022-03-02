import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.Lottos;
import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningCheckerTest {

    @Test
    @DisplayName("only 1등 당첨일 때 수익은 20억이어야 한다.")
    void checkWinning() {
        Lotto lotto = new Lotto(Arrays.asList(3, 5, 6, 7, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 9);
        int bonusNumber = 11;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(winningChecker.sumRewards()).isEqualTo(2000000000);

    }

    @Test
    @DisplayName("only 2등 당첨일 때 수익은 3000만이어야 한다.")
    void checkWinning2() {
        Lotto lotto = new Lotto(Arrays.asList(3, 5, 6, 7, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 10);
        int bonusNumber = 9;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(winningChecker.sumRewards()).isEqualTo(30000000);
    }

    @Test
    @DisplayName("only 3등 당첨일 때 수익은 150만이어야 한다.")
    void checkWinning3() {
        Lotto lotto = new Lotto(Arrays.asList(3, 5, 6, 7, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 10);
        int bonusNumber = 12;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(winningChecker.sumRewards()).isEqualTo(1500000);
    }

    @Test
    @DisplayName("당첨 안됐을때 수익은 0원이어야 한다.")
    void checkWinning4() {
        Lotto lotto = new Lotto(Arrays.asList(11, 12, 13, 14, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 10);
        int bonusNumber = 9;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(winningChecker.sumRewards()).isEqualTo(0);
    }

}
