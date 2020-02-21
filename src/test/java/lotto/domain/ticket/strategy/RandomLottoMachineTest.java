package lotto.domain.ticket.strategy;

import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLottoMachineTest {

    @DisplayName("입력 금액에 따른 티켓 출력 개수 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void buyTicket(int inputMoney, int result) {
        //given
        LottoMachine lottoMachine = new RandomLottoMachine();
        BettingMoney bettingMoney = new BettingMoney(inputMoney);

        //when
        List<LottoTicket> lottoTickets = lottoMachine.buyTickets(bettingMoney);

        //then
        assertThat(lottoTickets).hasSize(result);
    }
}
