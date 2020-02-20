package lotto.domain;

import lotto.domain.ticket.LottoStore;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.RealLottoStore;
import lotto.view.dto.BettingMoneyRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {


    @DisplayName("가격에 해당하는 티켓을 발급하는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test1(int money, int expect) {
        LottoStore lottoStore = new RealLottoStore();
        List<LottoTicket> lottoTickets = lottoStore.buyTicket(new BettingMoneyRequestDTO(money));

        assertThat(lottoTickets).hasSize(expect);
    }
}
