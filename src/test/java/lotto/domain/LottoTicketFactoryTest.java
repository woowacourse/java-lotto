package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketFactoryTest {

    @DisplayName("수동로또 생성")
    @Test
    void createManualLottoTickets() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1","2","3","4","5","6"));
        assertThat(LottoTicketFactory.createManualLottoTicket(numbers).getLottoTicket()).containsExactly(
                LottoNumber.createLottoNumber("1"),
                LottoNumber.createLottoNumber("2"),
                LottoNumber.createLottoNumber("3"),
                LottoNumber.createLottoNumber("4"),
                LottoNumber.createLottoNumber("5"),
                LottoNumber.createLottoNumber("6"));
    }

    @Test
    @DisplayName("입력받은 금액만큼 수동티켓과 자동티켓 생성")
    void createAutoAndManualLottoTickets() {
        LottoTicket ticket1 = LottoTicketFactory.createManualLottoTicket(Arrays.asList("1", "2", "3", "4", "5", "6"));
        LottoTicket ticket2 = LottoTicketFactory.createManualLottoTicket(Arrays.asList("11", "12", "13", "14", "15", "16"));
        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(ticket1, ticket2));
        assertThat(LottoTicketFactory.createLottoTicketsIncludingManualTickets(new Money(10000), lottoTickets).getLottoTickets())
                .contains(ticket1, ticket2)
                .hasSize(10);
    }

}