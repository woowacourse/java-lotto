package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.DefaultWinningPrizeStrategy;
import domain.strategy.WinningPrizeStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    private final Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final WinningTicket winningTicket = WinningTicket.of(winningNumbers, 7);
    private final NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final LottoTickets lottoTickets = LottoTickets.of(new LottoMoney(1000),
            numberGenerateStrategy);
    private final WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
        LottoResult lottoResult = LottoResult.of(lottoTickets, winningTicket, winningPrizeStrategy);
        assertThat(lottoResult.getCountOfWinning().get(WinningPrize.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 게임이 총 수익률을 잘 계산하는지 확인한다.")
    void checkRateOfReturn() {
        LottoResult lottoResult = LottoResult.of(lottoTickets, winningTicket, winningPrizeStrategy);
        assertThat(lottoResult.calculateLottoRateOfReturn())
                .isEqualTo(WinningPrize.FIRST.getPrizeMoney() / (double)(lottoTickets.getTickets().size()
                        * LottoTicket.TICKET_PRICE));
    }
}