package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓에 6개의 로또 넘버가 들어오지 않으면 에러를 던지는지 확인한다.")
    void checkTicketSizeError() {
        assertThatThrownBy(() -> LottoTicket.fromNumberValues(Set.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 티켓의 로또 번호는 6개여야 합니다.");
    }
}