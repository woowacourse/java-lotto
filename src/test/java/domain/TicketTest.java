package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {
    private Ticket ticket;

    @BeforeEach
    void initialize() {
        ticket = new Ticket(() -> Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));
    }

    @Test
    void 티켓생성() {
        assertThat(ticket.getLottoNumbers()).isEqualTo(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))
        );
    }
}
