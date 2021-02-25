package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    private WinningNumbers winningNumbers;
    private List<LottoTicket> lottoTickets;

    @BeforeEach
    public void setUp() {
        winningNumbers = new WinningNumbers(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7));
        lottoTickets = Arrays.asList(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 7)),
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 6, 7)),
                LottoTicket.valueOf(Arrays.asList(9, 10, 11, 12, 13, 14))
        );
    }

    @DisplayName("일치하는 번호 개수에 따른 통계값을 구한다.")
    @Test
    public void calculateStatistics() {
        LottoResult lottoResult = new LottoResult(winningNumbers, lottoTickets);
        Map<Rank, Integer> lottoStatisticsMap = lottoResult.result();
        assertThat(lottoStatisticsMap).isEqualTo(new HashMap<Rank, Integer>() {
            {
                put(Rank.FIRST, 1);
                put(Rank.SECOND, 2);
                put(Rank.THIRD, 0);
                put(Rank.FOURTH, 0);
                put(Rank.FIFTH, 0);
                put(Rank.NOTHING, 1);
            }
        });
    }

    @DisplayName("상금을 계산한다.")
    @Test
    public void calculateRewardTest() {
        LottoResult lottoResult = new LottoResult(winningNumbers, lottoTickets);
        Money reward = lottoResult.getReward();
        assertThat(reward.toLong()).isEqualTo(2_060_000_000);
    }
}
