package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    @Test
    @DisplayName("당첨번호가 몇개있는지 확인")
    void howManyWins() {
        Lotto generatedLotto = new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 8, 9", "10");
        assertThat(winningLotto.howManyWins(generatedLotto)).isEqualTo(4);
    }

    @Test
    @DisplayName("몇등에 당첨인지 확인")
    void findRank() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto lotto = new Lotto(numbers);
        Rank rank = winningLotto.findRank(lotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
