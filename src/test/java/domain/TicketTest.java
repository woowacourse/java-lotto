package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class TicketTest {
    @Test
    void 티켓생성() {
        Ticket ticket = new Ticket(() -> Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)));

        assertThat(ticket.getLottoNumbers()).isEqualTo(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))
        );
    }

    @Test
    void 티켓생성2() {
        Ticket ticket = new Ticket(() -> Set.of(new LottoNumber(40),
            new LottoNumber(39),
            new LottoNumber(19),
            new LottoNumber(23),
            new LottoNumber(3),
            new LottoNumber(17)));

        assertThat(ticket.getLottoNumbers()).isEqualTo(Set.of(new LottoNumber(40),
            new LottoNumber(39),
            new LottoNumber(19),
            new LottoNumber(23),
            new LottoNumber(3),
            new LottoNumber(17))
        );
    }

    @Test
    void 일치한_로또번호_개수확인_6개일치() {
        Ticket ticket = new Ticket(() -> Set.of(new LottoNumber(40),
            new LottoNumber(39),
            new LottoNumber(19),
            new LottoNumber(23),
            new LottoNumber(3),
            new LottoNumber(17)));

        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(40),
            new LottoNumber(39),
            new LottoNumber(19),
            new LottoNumber(23),
            new LottoNumber(3),
            new LottoNumber(17));

        assertThat(ticket.getSameLottoNumberCount(lottoNumbers)).isEqualTo(6);
    }

    @Test
    void 일치한_로또번호_개수확인_1개일치() {
        Ticket ticket = new Ticket(() -> Set.of(new LottoNumber(40),
            new LottoNumber(39),
            new LottoNumber(19),
            new LottoNumber(23),
            new LottoNumber(3),
            new LottoNumber(17)));

        Set<LottoNumber> lottoNumbers = Set.of(new LottoNumber(1),
            new LottoNumber(43),
            new LottoNumber(24),
            new LottoNumber(14),
            new LottoNumber(9),
            new LottoNumber(40));

        assertThat(ticket.getSameLottoNumberCount(lottoNumbers)).isEqualTo(1);
    }
}
