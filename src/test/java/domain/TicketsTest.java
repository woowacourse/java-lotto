package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class TicketsTest {

    @Test
    void 티켓_정상_생성() {
        List<List<Integer>> manualNumber = Arrays.asList(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6));
        Tickets tickets = new Tickets(manualNumber.stream()
                .map(Ticket::from)
                .collect(Collectors.toList()));
        tickets.addAutoTickets(3, new RandomLottoNumbersGenerator());
        assertThat(tickets.getTickets().size()).isEqualTo(5);
    }
}
