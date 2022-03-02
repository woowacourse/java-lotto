package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTicketTest {

    @Test
    @DisplayName("로또번호가 6개가 아닌 경우 오류를 발생합니다.")
    void checkLottoNumberSize() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3));
        assertThatThrownBy(() -> WinningTicket.from(numbers, 10))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(LottoTicket.LOTTO_TICKET_SIZE_ERROR_MESSAGE);
    }
}