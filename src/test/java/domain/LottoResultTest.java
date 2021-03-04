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
    private LottoTickets lottoTickets;

    @BeforeEach
    public void setUp() {
        winningNumbers = WinningNumbers.of(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoNumber.from(7));
        lottoTickets = LottoTickets.of(Arrays.asList(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 7)),
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 6, 7)),
                LottoTicket.valueOf(Arrays.asList(9, 10, 11, 12, 13, 14))
        ));
    }

    @DisplayName("상금을 계산한다.")
    @Test
    public void calculateRewardTest() {
        LottoResult lottoResult = lottoTickets.generateLottoResult(winningNumbers);
        Money reward = lottoResult.getReward();
        assertThat(reward.toLong()).isEqualTo(2_060_000_000);
    }
}
