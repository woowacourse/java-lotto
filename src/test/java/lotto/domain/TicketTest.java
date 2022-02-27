package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

public class TicketTest {

    @Test
    @DisplayName("티켓 정상 생성")
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
    @DisplayName("티켓 입력 정상일때")
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
    @DisplayName("티켓 번호 개수 5개일때 예외테스트")
    void 티켓번호개수_5개() {
        assertThatThrownBy(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5))))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
