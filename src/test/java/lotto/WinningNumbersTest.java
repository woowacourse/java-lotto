package lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningNumbersTest {

    @Test
    void compareLottos() {
        List<Integer> sixNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumbers winningNumbers = new WinningNumbers(sixNumbers, bonusNumber);

        List<Lotto> lottos = Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 12)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12)),
                new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12)),
                new Lotto(Arrays.asList(1, 2, 9, 10, 11, 12))
                );

        List<Rank> givenRanks = winningNumbers.compareLottos(lottos);
        List<Rank> expectedRanks = Arrays.asList(Rank.FIRST, Rank.THIRD, Rank.FOURTH, Rank.FIFTH);

        assertThat(givenRanks).isEqualTo(expectedRanks);
    }
}