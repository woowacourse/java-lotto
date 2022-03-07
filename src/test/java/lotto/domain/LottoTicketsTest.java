package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import lotto.TestLottoFactory;
import lotto.utils.NumberGenerator;
import lotto.utils.RandomNumberGenerator;
import lotto.utils.StringNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    private static LottoTickets lottoTickets;
    private static NumberGenerator generator;
    private static Money money;

    @BeforeEach
    void setUp() {
        money = new Money(3000);
         generator = new RandomNumberGenerator(LottoNumber.MIN,
            LottoNumber.MAX);
        lottoTickets = LottoTickets.buyTicket(generator, money.count());
    }

    @Test
    @DisplayName("로또 티켓들이 정상적으로 생성되는지 확인")
    void createLottoTickets() {
        // given
        List<LottoTicket> tickets = lottoTickets.getTickets();
        // then
        assertThat(tickets.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또 티켓이 정상적으로 생성되는지 확인")
    void createManualLotto() {
        // given
        String input = "1, 2, 3, 4, 5, 6";
        LottoTicket expected = new LottoTicket(TestLottoFactory.getNumbers(1, 2, 3, 4, 5, 6));
        LottoTickets manualTicket = LottoTickets.buyTicket(new StringNumberGenerator(input), 1);
        // then
        assertThat(manualTicket.getTickets()).contains(expected);
    }
}
