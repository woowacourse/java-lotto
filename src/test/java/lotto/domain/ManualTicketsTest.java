package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ManualTicketsTest {

    @DisplayName("입력받은 String 배열의 리스트를 Integer로 파싱하여 내부 컬렉션에 저장한다")
    @Test
    void make() {
        String[] split = "1, 2, 3, 4, 5, 6".split(", ");
        List<String[]> inputNumbers = Arrays.asList(split, split);

        ManualTickets manualTickets = ManualTickets.from(inputNumbers);
        int manualTicketCounts = manualTickets.size();

        assertThat(manualTicketCounts).isEqualTo(2);
    }
}
