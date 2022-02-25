package model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {

    private final LottoGame lottoGame = new LottoGame();
    private final int purchaseMoney = 17000;
    private final GenerateStrategy generateStrategy = () -> Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    @DisplayName("생성된 로또 티켓이 저장되는지 확인한다.")
    void checkGenerateLottoTicket() {
        lottoGame.insertMoney(purchaseMoney);
        lottoGame.purchaseLottoTickets(generateStrategy);

        assertThat(lottoGame.lottoTickets().size()).isEqualTo(purchaseMoney / 1000);
    }

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 7;

        lottoGame.insertMoney(purchaseMoney);
        lottoGame.purchaseLottoTickets(generateStrategy);
        lottoGame.insertWinningNumbers(winningNumbers, bonusNumber);

        assertThat(lottoGame.winningResult().get(WinningRank.FIRST)).isEqualTo(17);
    }
}