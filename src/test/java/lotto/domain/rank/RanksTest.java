package lotto.domain.rank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.lotto.LottoNumbers;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.winning.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RanksTest {

    @Test
    @DisplayName("총 획득 상금 계산")
    void getTotalWinnings() {
        WinningNumbers winningNumbers = WinningNumbers.valueOf("1, 2, 3, 4, 5, 6", "7");
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.valueOf("1,2,3,4,5,6"),
            LottoNumbers.valueOf("1,2,3,4,5,7"),
            LottoNumbers.valueOf("1,2,3,4,5,34"),
            LottoNumbers.valueOf("1,2,3,4,11,12"),
            LottoNumbers.valueOf("1,2,3,11,12,13")
        ));

        Ranks ranks = lottoTicket.calculateRankings(winningNumbers);

        Long expected = Arrays.stream(Rank.values())
            .mapToLong(Rank::getWinnings)
            .sum();

        assertThat(ranks.getTotalWinnings()).isEqualTo(expected);
    }
}
