package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketFactoryTest {
    private LottoTicketFactory lottoTicketFactory;

    @BeforeEach
    void setUp() {
        lottoTicketFactory = new LottoTicketFactory();
    }

    @Test
    @DisplayName("입력받은 금액만큼 자동티켓 생성")
    void createLottoTickets() {
        assertThat(lottoTicketFactory.buyLottoTickets(new Money(10000)).size()).isEqualTo(10);
    }

    @DisplayName("수동로또 생성")
    @Test
    void createManualLottoTickets() {
        List<String> numbers = new ArrayList<>(Arrays.asList("1","2","3","4","5","6"));
        assertThat(lottoTicketFactory.createManualLottoTicket(numbers)).containsExactly(
                new LottoNumber("1"),
                new LottoNumber("2"),
                new LottoNumber("3"),
                new LottoNumber("4"),
                new LottoNumber("5"),
                new LottoNumber("6"));
    }

    @Test
    @DisplayName("입력받은 금액만큼 수동티켓과 자동티켓 생성")
    void createAutoAndManualLottoTickets() {
        LottoTicket ticket1 = new LottoTicket(lottoTicketFactory.createManualLottoTicket(Arrays.asList("1", "2", "3", "4", "5", "6")));
        LottoTicket ticket2 = new LottoTicket(lottoTicketFactory.createManualLottoTicket(Arrays.asList("11", "12", "13", "14", "15", "16")));
        List<LottoTicket> lottoTickets = new ArrayList<>(Arrays.asList(ticket1, ticket2));
        assertThat(lottoTicketFactory.buyLottoTicketsIncludingManualTickets(new Money(10000), lottoTickets))
                .contains(ticket1, ticket2)
                .hasSize(10);
    }

}