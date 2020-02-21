package lotto.domain.ticket;

import lotto.domain.ticket.strategy.LottoMachine;
import lotto.domain.ticket.strategy.RandomLottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoBallFactoryTest {


    @DisplayName("가격에 해당하는 티켓을 발급하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test1(int money, int expect) {
        LottoMachine randomLottoStore = new RandomLottoMachine();
        List<LottoTicket> lottoTickets = randomLottoStore.buyTickets(new BettingMoney(money));

        assertThat(lottoTickets).hasSize(expect);
    }
}
