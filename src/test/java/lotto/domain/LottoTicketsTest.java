package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @Test
    @DisplayName("입력받은 돈에 따른 올바른 티켓 갯수를 생성하는지")
    void ticketQuantity() {
        assertThat(LottoTickets.createLottoTickets(new Money("5000")).getLottoTickets().size()).isEqualTo(5);
    }

}