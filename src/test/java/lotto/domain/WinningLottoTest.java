package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    private static Lotto generatedLotto;
    private static WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        Lotto winLotto = new Lotto(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6")));
        generatedLotto = new Lotto(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "7")));
        winningLotto = new WinningLotto(winLotto, "7");
    }

    @Test
    @DisplayName("당첨번호가 몇개있는지 확인")
    void howManyWins() {
        assertThat(winningLotto.howManyWins(generatedLotto)).isEqualTo(5);
    }

    @Test
    @DisplayName("몇등에 당첨인지 확인")
    void findRank() {
        final Rank rank = winningLotto.findRank(generatedLotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
