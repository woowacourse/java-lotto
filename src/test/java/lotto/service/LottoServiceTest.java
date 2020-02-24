package lotto.service;

import lotto.domain.ticket.LottoMachineForTest;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.view.dto.BettingMoneyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: getResultDTO()테스트
public class LottoServiceTest {
    private LottoService service;

    @BeforeEach
    void setUp() {
        service = new LottoService(new LottoMachineForTest());
    }

    @DisplayName("티켓 번들 생성 확인")
    @Test
    void name() {
        //given
        List<LottoTicket> lottoTickets = new LottoMachineForTest().buyTickets(1000);
        LottoTicketBundle expectedTicketBundle = new LottoTicketBundle(lottoTickets);

        //when
        LottoTicketBundle ticketBundle = service.createLottoTicketBundle(new BettingMoneyDTO(1000));

        //then
        assertThat(ticketBundle).isEqualTo(expectedTicketBundle);
    }
}
