package domain;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketTest {
    @Test
    void 티켓생성() {
        Ticket ticket = new Ticket(() -> Arrays.asList(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));

        Assertions.assertThat(ticket.getLottoNumbers()).isEqualTo(Arrays.asList(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))
        );
    }

    @Test
    void 티켓생성2() {
        Ticket ticket = new Ticket(() -> Arrays.asList(new LottoNumber(40),
            new LottoNumber(39),
            new LottoNumber(19),
            new LottoNumber(23),
            new LottoNumber(3),
            new LottoNumber(17)));

        Assertions.assertThat(ticket.getLottoNumbers()).isEqualTo(Arrays.asList(new LottoNumber(40),
            new LottoNumber(39),
            new LottoNumber(19),
            new LottoNumber(23),
            new LottoNumber(3),
            new LottoNumber(17))
        );
    }
}
