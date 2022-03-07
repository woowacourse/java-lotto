import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.Lottos;
import domain.Rewards;
import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    @Test
    @DisplayName("winningCount 6개 일시 1등 당첨")
    void winning_checker_check_FIRST_REWARD() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 11);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.FIRST_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 5개 & bonusCount 1개 일시 2등 당첨")
    void winning_checker_check_SECOND_REWARD() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 7), 6);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.SECOND_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 5개 & bonusCount 0개 일시 3등 당첨")
    void winning_checker_check_THIRD_REWARD() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 7), 11);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.THIRD_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 4개 & bonusCount 0개 일시 4등 당첨")
    void winning_checker_check_FOURTH_REWARD_1() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 7, 8), 11);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.FOURTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 4개 & bonusCount 1개 일시 4등 당첨")
    void winning_checker_check_FOURTH_REWARD_2() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 8, 9), 5);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.FOURTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 3개 & bonusCount 0개 일시 5등 당첨")
    void winning_checker_check_FIFTH_REWARD_1() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 7, 8, 9), 10);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.FIFTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 3개 & bonusCount 1개 일시 5등 당첨")
    void winning_checker_check_FIFTH_REWARD_2() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 7, 8, 9), 4);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.FIFTH_REWARD)).isEqualTo(1);

    }

    @Test
    @DisplayName("winningCount 3개 미만 일시 당첨 아님")
    void winning_checker_check_NO_REWARD() {

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 10, 11, 12, 13), 4);
        WinningChecker winningChecker = new WinningChecker(new Lottos(List.of(lotto)),
            winningNumbers);

        winningChecker.check();

        assertThat(winningChecker.getRewardsCount(Rewards.NO_REWARD)).isEqualTo(1);

    }


}
