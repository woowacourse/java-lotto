package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ManualTicketTest {

    @DisplayName("입력받은 String 배열을 Integer로 파싱하여 내부 컬렉션에 저장한다")
    @Test
    void make() {
        String[] split = "1, 2, 3, 4, 5, 6".split(", ");

        ManualTicket manualTickets = ManualTicket.from(split);
        List<Integer> inputNumbers = manualTickets.getManualTicketNumbers();

        assertThat(inputNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
