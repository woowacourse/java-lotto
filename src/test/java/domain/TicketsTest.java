package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class TicketsTest {

    @Test
    void 티켓_정상_생성() {
        /* given */
        List<Ticket> manualTicket =
                Arrays.asList(new Ticket(new ManualLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6))));

        List<Ticket> autoTicket = Arrays.asList(new Ticket(new RandomLottoNumbersGenerator()),
                new Ticket(new RandomLottoNumbersGenerator()));

        List<Ticket> allTickets = new ArrayList<>();
        allTickets.addAll(manualTicket);
        allTickets.addAll(autoTicket);

        /* when */
        assertThat(allTickets.size())
                /* then */
                .isEqualTo(3);
    }
}
