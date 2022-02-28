import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.Lottos;
import domain.Rewards;
import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningCheckerTest {

    @BeforeEach
    void setup() {
        Rewards.removeCount();
    }

    @Test
    @DisplayName("1등 당첨인지 확인하는 기능 테스트")
    void checkWinning() {
        Lotto lotto = new Lotto(Arrays.asList(3, 5, 6, 7, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 9);
        int bonusNumber = 11;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(Rewards.getCount(Rewards.FIRST_REWARD))
            .isEqualTo(1);
    }

    @Test
    @DisplayName("2등 당첨인지 확인하는 기능 테스트")
    void checkWinning2() {
        Lotto lotto = new Lotto(Arrays.asList(3, 5, 6, 7, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 10);
        int bonusNumber = 9;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(Rewards.getCount(Rewards.SECOND_REWARD))
            .isEqualTo(1);
    }

    @Test
    @DisplayName("3등 당첨인지 확인하는 테스트 ")
    void checkWinning3() {
        Lotto lotto = new Lotto(Arrays.asList(3, 5, 6, 7, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 10);
        int bonusNumber = 12;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(Rewards.getCount(Rewards.THIRD_REWARD))
            .isEqualTo(1);
    }

    @Test
    @DisplayName("당첨 안됐을때 확인하는 기능 테스트")
    void checkWinning4() {
        Lotto lotto = new Lotto(Arrays.asList(11, 12, 13, 14, 8, 9));
        List<Integer> winningNumber = Arrays.asList(3, 5, 6, 7, 8, 10);
        int bonusNumber = 9;

        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            new WinningNumbers(winningNumber, bonusNumber));

        winningChecker.check();

        assertThat(Rewards.getCount(Rewards.NO_REWARD))
            .isEqualTo(1);
    }

}
