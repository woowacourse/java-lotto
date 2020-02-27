package lotto.domain.ticket.strategy;

import lotto.domain.ticket.BettingInfo;
import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLottoMachineTest {

    @DisplayName("가격에 해당하는 티켓을 발급하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test1(int money, int expect) {
        BettingMoney bettingMoney = BettingMoney.valueOf(money);
        BettingInfo bettingInfo = new BettingInfo(bettingMoney, 0);

        LottoMachine randomLottoStore = new RandomLottoMachine(bettingInfo.getRandomAmount());

        List<LottoTicket> lottoTickets = randomLottoStore.buyTickets();

        assertThat(lottoTickets).hasSize(expect);
    }
}
