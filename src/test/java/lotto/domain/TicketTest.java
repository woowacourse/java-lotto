package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.utils.BasicLottoNumberGenerator;

public class TicketTest {

    @Test
    @DisplayName("티켓생성시 LottoNumber의 숫자와 개수가 정상이면 예외를 던지지 말아야 합니다.")
    void ticketCreateValidTest() {
        assertThatCode(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("티켓생성시 LottoNumber의 개수가 5개면 예외를 던져야 합니다.")
    void ticketCreateInvalidTest() {
        assertThatThrownBy(() -> new Ticket(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5))))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("티켓 끼리의 비교는 LottoNumber의 숫자가 같으면 같은 티켓으로 판단합니다.")
    void ticketCompareTest() {
        Ticket ticket = new Ticket(new BasicLottoNumberGenerator());
        assertThat(ticket.getLottoNumbers()).isEqualTo(Set.of(new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6))
        );
    }
}
