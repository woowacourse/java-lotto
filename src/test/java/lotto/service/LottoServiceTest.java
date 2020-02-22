package lotto.service;

import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.strategy.LottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static lotto.LottoHelper.lottoBalls;
import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private static Stream<Arguments> ticketProvider() {
        LottoTicket lottoTicket = new LottoTicket(lottoBalls(Arrays.asList(1, 2, 3, 4, 5, 6)));

        return Stream.of(
                Arguments.of(1000, Arrays.asList(lottoTicket)),
                Arguments.of(2000, Arrays.asList(lottoTicket, lottoTicket))
        );
    }

    @DisplayName("넣은 금액으로 로또 티켓 생성 확인")
    @ParameterizedTest
    @MethodSource("ticketProvider")
    void getLottoTicketBundle(int bettingMoney, List<LottoTicket> resultTickets) {
        //given
        BettingMoney bettingMoneyRequestDTO = BettingMoney.valueOf(bettingMoney);

        LottoMachine lottoMachine = money -> resultTickets;
        LottoService lottoService = new LottoService(lottoMachine);

        LottoTicketBundle result = new LottoTicketBundle(resultTickets);

        //when
        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoneyRequestDTO);

        //then
        assertThat(lottoTicketBundle).isEqualTo(result);
    }

}