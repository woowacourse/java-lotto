package domain.tickets;

import domain.LottoMoney;
import domain.TicketQuantity;
import domain.ticket.LottoTicket;
import domain.ticket.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoTicketsTest {
    private LottoMoney lottoMoney;

    @BeforeEach
    void setUp() {
        this.lottoMoney = new LottoMoney("14000");
    }

    @DisplayName("자동 티켓 객체 생성 성공")
    @Test
    void create_autoTickets_success() {
        final int expectedAutoAmount = 14;
        final int expectedManualAmount = 0;
        final TicketQuantity ticketQuantity = new TicketQuantity(lottoMoney, expectedManualAmount);

        assertAll(
                () -> assertThatCode(() -> new LottoTickets(ticketQuantity))
                        .doesNotThrowAnyException(),

                () -> assertThat(new LottoTickets(ticketQuantity)
                        .isSameTotalQuantity(expectedAutoAmount))
                        .isEqualTo(true)
        );
    }

    @DisplayName("수동 티켓 객체 생성 성공")
    @Test
    void create_manualTickets_success() {
        final int expectedManualAmount = 3;
        final TicketQuantity ticketQuantity = new TicketQuantity(lottoMoney, expectedManualAmount);

        final List<List<Integer>> expectedManualNumbers = createDummyManualNumbers();
        final List<Ticket> dummyManualTickets = createDummyManualTickets(expectedManualNumbers);

        assertAll(
                () -> assertThatCode(() -> new LottoTickets(ticketQuantity, expectedManualNumbers))
                        .doesNotThrowAnyException(),

                () -> assertThat(new LottoTickets(ticketQuantity, expectedManualNumbers)
                        .toList()
                        .subList(0, expectedManualAmount))
                        .isEqualTo(dummyManualTickets)
        );
    }

    private List<Ticket> createDummyManualTickets(final List<List<Integer>> expectedManualNumbers) {
        return IntStream.range(0, expectedManualNumbers.size())
                .mapToObj(i -> new LottoTicket(expectedManualNumbers.get(i)))
                .collect(Collectors.toList());
    }

    private List<List<Integer>> createDummyManualNumbers() {
        return Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(7, 8, 9, 10, 11, 12),
                Arrays.asList(13, 14, 15, 16, 17, 18)
        );
    }
}
