package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    private WinningNumbers winningNumbers;
    private LottoTicket myLottoTicket;

    @BeforeEach
    void setUpWinningNumbersAndLottoTicket() {
        Number bonusNumber = new Number(7);
        Lotto winningNumber = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers = new WinningNumbers(winningNumber, bonusNumber);
        myLottoTicket = new LottoTicket(Arrays.asList(
                Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
                Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 7))
        ));
    }

    @Test
    @DisplayName("로또 티켓은 순위별 당첨 수를 계산한다")
    void findWinningCountByRank() {
        EnumMap<LottoRank, Integer> expectedWinningCountByRank = new EnumMap<>(Map.ofEntries(
                Map.entry(LottoRank.FIRST, 1),
                Map.entry(LottoRank.SECOND, 1),
                Map.entry(LottoRank.THIRD, 0),
                Map.entry(LottoRank.FOURTH, 0),
                Map.entry(LottoRank.FIFTH, 0)
        ));

        final EnumMap<LottoRank, Integer> actual = myLottoTicket.findWinningCountByRank(winningNumbers);

        assertThat(expectedWinningCountByRank).isEqualTo(actual);
    }
}
