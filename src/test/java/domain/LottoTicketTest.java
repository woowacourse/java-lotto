package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓에 6개의 로또 넘버가 들어오지 않으면 에러를 던지는지 확인한다.")
    void checkTicketSizeError() {
        assertThatThrownBy(() -> LottoTicket.from(Set.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoTicket.LOTTO_TICKET_SIZE_ERROR_MESSAGE);
    }
}