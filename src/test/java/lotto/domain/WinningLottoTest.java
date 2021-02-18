package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {
    @Test
    void 당첨번호가_몇개있는지_확인() {
        Lotto generatedLotto = new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 8, 9", "10");
        assertThat(WinningLotto.howManyWins(generatedLotto)).isEqualTo(4);
    }


    @Test
    void 몇등에_당첨인지_확인() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto lotto = new Lotto(numbers);
        Rank rank = winningLotto.findRank(lotto);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }
}
