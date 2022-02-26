package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private final WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

    @Test
    @DisplayName("로또 1등 당첨인 경우")
    void firstRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("로또 2등 당첨인 경우")
    void secondRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("로또 3등 당첨인 경우")
    void thirdRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("로또 4등 당첨인 경우")
    void fourthRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("로또 5등 당첨인 경우")
    void fifthRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("로또 미당첨인 경우")
    void noRankTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 8, 9, 10, 11));
        assertThat(lotto.calculateRank(winningLotto)).isEqualTo(Rank.LOSER);
    }
}
