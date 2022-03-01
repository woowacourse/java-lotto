package domain;

import static domain.LottoTickets.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {
    @Test
    @DisplayName("생성된 로또 티켓이 저장되는지 확인한다.")
    void checkGenerateTicket() {
        int purchaseMoney = 17000;
        Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        LottoTickets lottoTickets = new LottoTickets(purchaseMoney, new LottoNumberGenerateStrategy());
        WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);
        WinningPrizeStrategy winningPrizeStrategy = new DefaultLottoWinningPrizeStrategy();
        LottoGame lottoGame = new LottoGame(lottoTickets, winningTicket, winningPrizeStrategy);
        assertThat(lottoGame.getTickets().size()).isEqualTo(purchaseMoney / TICKET_PRICE);
    }

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
        int purchaseMoney = 14000;
        Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        GenerateStrategy generateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTickets lottoTickets = new LottoTickets(purchaseMoney, generateStrategy);
        WinningTicket winningTicket = new WinningTicket(winningNumbers, bonusNumber);
        WinningPrizeStrategy winningPrizeStrategy = new DefaultLottoWinningPrizeStrategy();
        LottoGame lottoGame = new LottoGame(lottoTickets, winningTicket, winningPrizeStrategy);
        assertThat(lottoGame.getWinningResult().getCountOfWinning().get(WinningPrize.FIRST)).isEqualTo(14);
    }
}