package model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningTicketTest {

    @Test
    @DisplayName("로또번호가 6개가 아닌 경우 오류를 발생합니다.")
    void checkLottoNumberSize() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        assertThatThrownBy(() -> new WinningTicket(numbers, 10)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}