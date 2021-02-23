package lotto.domain.rank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.lotto.LottoNumbers;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningNumbers;
import lotto.domain.number.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RanksTest {

    Ranks ranks;

    @BeforeEach
    void setUp() {
        WinningNumbers winningNumbers = new WinningNumbers(LottoNumbers.valueOf("1,2,3,4,5,6"), LottoNumber
            .valueOf("7"));
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(
            LottoNumbers.valueOf("1,2,3,4,5,6"),
            LottoNumbers.valueOf("1,2,3,4,5,7"),
            LottoNumbers.valueOf("1,2,3,4,5,34"),
            LottoNumbers.valueOf("1,2,3,4,11,12"),
            LottoNumbers.valueOf("1,2,3,11,12,13")
        ));

        ranks = lottoTicket.calculateRanks(winningNumbers);
    }

    @Test
    @DisplayName("총 획득 상금 계산")
    void getTotalWinnings() {
        Long expected = Arrays.stream(Rank.values())
            .mapToLong(Rank::getWinnings)
            .sum();

        assertThat(ranks.getTotalWinnings()).isEqualTo(expected);
    }

    @Test
    @DisplayName("결과 개수 반환")
    void getRanksCount() {
        assertThat(ranks.getRanksCount()).isEqualTo(5);
    }
}
