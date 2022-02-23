package model;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {
    @Test
    @DisplayName("생성된 로또 티켓이 저장되는지 확인한다.")
    void checkGenerateTicket() {
        int purchaseMoney = 17000;
        LottoGame lottoGame = new LottoGame(purchaseMoney);
        assertThat(lottoGame.getTickets().size()).isEqualTo(purchaseMoney / 1000);
    }

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
       int purchaseMoney = 14000;
       List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
       int bonusNumber = 7;
       GenerateStrategy generateStrategy = (GenerateStrategy) () -> {
            return Arrays.asList(1,2,3,4,5,6);
       };
       LottoGame lottoGame = new LottoGame(purchaseMoney, winningNumbers, bonusNumber, generateStrategy);

       Assertions.assertThat(lottoGame.prizeResult().get(WinningPrize.FIRST)).isEqualTo(14);
    }

}