package lotto.service;

import lotto.domain.ticket.BettingInfo;
import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.manual.ManualNumberBundle;
import lotto.domain.ticket.strategy.LottoMachine;
import lotto.domain.ticket.strategy.ManualLottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static lotto.LottoHelper.lottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private static Stream<Arguments> ticketProvider() {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers(1, 2, 3, 4, 5, 6));

        return Stream.of(
                Arguments.of(1000, Arrays.asList(lottoTicket)),
                Arguments.of(2000, Arrays.asList(lottoTicket, lottoTicket))
        );
    }

    @DisplayName("넣은 금액으로 자동 로또 티켓 생성 확인")
    @ParameterizedTest
    @MethodSource("ticketProvider")
    void getLottoTicketBundle(int money, List<LottoTicket> resultTickets) {
        //given
        BettingMoney bettingMoney = BettingMoney.valueOf(money);
        BettingInfo bettingInfo = new BettingInfo(bettingMoney, 0);

        LottoMachine lottoMachine = (ticketCount, bundle) -> resultTickets;
        LottoService lottoService = new LottoService(lottoMachine, new ManualLottoMachine());

        LottoTicketBundle result = new LottoTicketBundle(resultTickets);

        //when
        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingInfo, new ManualNumberBundle(Collections.emptyList()));

        //then
        assertThat(lottoTicketBundle).isEqualTo(result);
    }

}