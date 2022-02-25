package model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import model.LottoNumberGenerator.GenerateStrategy;
import model.winning.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    private final LottoMachine lottoMachine = new LottoMachine();
    private final int purchaseMoney = 17000;
    private final GenerateStrategy generateStrategy = () -> Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    @DisplayName("생성된 로또 티켓이 저장되는지 확인한다.")
    void checkGenerateLottoTicket() {
        lottoMachine.insertMoney(purchaseMoney);
        lottoMachine.purchaseLottoTickets(generateStrategy);

        assertThat(lottoMachine.lottoTickets().size()).isEqualTo(purchaseMoney / 1000);
    }

    @Test
    @DisplayName("당첨 결과 카운팅이 정상적으로 되는지 확인한다.")
    void winningCountResultTest() {
        final List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 7;

        lottoMachine.insertMoney(purchaseMoney);
        lottoMachine.purchaseLottoTickets(generateStrategy);
        lottoMachine.insertWinningNumbers(winningNumbers, bonusNumber);

        assertThat(lottoMachine.winningResult().get(Rank.FIRST)).isEqualTo(17);
    }
}