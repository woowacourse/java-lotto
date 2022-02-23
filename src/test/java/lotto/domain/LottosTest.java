package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("구입금액 만큼 로또티켓을 구입")
    void buyTicketsByAuto() {
        Lottos lottos = Lottos.buyTicketsByAuto(new Money("10000"));
        assertThat(lottos.getTickets().size()).isEqualTo(10);
    }
}
