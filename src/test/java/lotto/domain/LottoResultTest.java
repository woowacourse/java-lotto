package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    LottoResult lottoResult = new LottoResult();
    LottoTickets lottoTickets;
    WinningLotto winningLotto;

    @BeforeEach
    void init() {
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottoTickets = new LottoTickets(Collections.singletonList(lottoTicket));
        LottoTicket winningTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7));
        winningLotto = new WinningLotto(winningTicket, new LottoNumber(6));
    }

    @DisplayName("당첨티켓과 구매티켓들을 각각 비교하여 각 등수별 당첨횟수를 구한다.")
    @Test
    void checkResult() {
        lottoResult.checkWinnings(lottoTickets, winningLotto);
    }

    @DisplayName("저장된 등수별 당첨횟수를 이용해 총 당청금을 게산한다.")
    @Test
    void calculate() {
        lottoResult.checkWinnings(lottoTickets, winningLotto);
        assertThat(lottoResult.calculateTotalReward()).isEqualTo(30_000_000);
    }
}
