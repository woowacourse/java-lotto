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

    @Test
    void 티켓번호개수_정상입력() {
        assertThatCode(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))))
            .doesNotThrowAnyException();
    }

    @Test
    void 티켓번호개수_5개() {
        assertThatThrownBy(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_문자열로_로또번호_정상생성() {
        assertThatCode(() -> Ticket.of("1, 2, 3, 4, 5, 6"))
            .doesNotThrowAnyException();
    }

    @Test
    void 입력받은_문자열로_로또번호_5개입력() {
        assertThatThrownBy(() -> Ticket.of("1, 2, 3, 4, 5"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_문자열로_로또번호_7개입력() {
        assertThatThrownBy(() -> Ticket.of("1, 2, 3, 4, 5, 6, 7"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_문자열로_로또번호_문자열() {
        assertThatThrownBy(() -> Ticket.of("1, 2, 3, 4, a, 6"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_문자열로_로또번호_중복() {
        assertThatThrownBy(() -> Ticket.of("1, 2, 3, 4, 5, 5"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_문자열로_로또번호_마지막구분자() {
        assertThatThrownBy(() -> Ticket.of("1, 2, 3, 4, 5, 6, "))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력받은_문자열로_로또번호_널() {
        assertThatThrownBy(() -> Ticket.of(null))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
