package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.DefaultWinningPrizeStrategy;
import domain.strategy.WinningPrizeStrategy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
        Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningTicket winningTicket = WinningTicket.create(winningNumbers, 7);
        NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTickets lottoTickets = LottoTickets.generateAutoTickets(new LottoMoney(1000),
                numberGenerateStrategy);
        WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();
        WinningResult winningResult = WinningResult.toExtract(lottoTickets, winningTicket, winningPrizeStrategy);
        assertThat(winningResult.getCountOfWinning().get(WinningPrize.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 게임이 총 수익률을 잘 계산하는지 확인한다.")
    void checkRateOfReturn() {
        LottoMoney purchaseMoney = new LottoMoney(1000);
        Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningTicket winningTicket = WinningTicket.create(winningNumbers, 7);
        NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTickets lottoTickets = LottoTickets.generateAutoTickets(purchaseMoney, numberGenerateStrategy);
        WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();
        WinningResult winningResult = WinningResult.toExtract(lottoTickets, winningTicket, winningPrizeStrategy);

        assertThat(winningResult.calculateLottoRateOfReturn(purchaseMoney))
                .isEqualTo(WinningPrize.FIRST.getPrizeMoney() / (double)(lottoTickets.getTickets().size()
                        * LottoGame.TICKET_PRICE));
    }
}