package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ManualTicketNumbersTest {

    @DisplayName("String 배열을 받으면 내부 Integer 컬렉션에 번호들을 저장한다")
    @Test
    void makeManualTicketNumbers() {
        String[] numbers = "1, 2, 3, 4, 5, 6".split(", ", -1);

        ManualTicketNumbers manualTicketNumbers = ManualTicketNumbers.from(numbers);
        List<Integer> parsedNumbers = manualTicketNumbers.getManualTicketNumbers();

        assertThat(parsedNumbers).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
