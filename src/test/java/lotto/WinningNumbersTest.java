package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
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

        List<LottoTicket> lottoTickets = Arrays.asList(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 12)),
                new LottoTicket(Arrays.asList(1, 2, 3, 4, 11, 12)),
                new LottoTicket(Arrays.asList(1, 2, 3, 10, 11, 12)),
                new LottoTicket(Arrays.asList(1, 2, 9, 10, 11, 12))
                );

        List<Rank> givenRanks = winningNumbers.compareLottos(lottoTickets);
        List<Rank> expectedRanks = Arrays.asList(Rank.FIRST, Rank.THIRD, Rank.FOURTH, Rank.FIFTH);

        assertThat(givenRanks).isEqualTo(expectedRanks);
    }
}