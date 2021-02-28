package lotto.ticket;

import lotto.ticket.strategy.ManualNumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_INPUT;
import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_RANGE;
import static lotto.ticket.Ticket.ERROR_MESSAGE_DUPLICATED;
import static lotto.ticket.Ticket.ERROR_MESSAGE_INVALID_SIZE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ManualNumbersGeneratorTest {
    @ParameterizedTest
    @DisplayName("잘못된 구분자를 사용한 경우")
    @ValueSource(strings = {"1, 2, 3, 4, 5. 6", "1#2,3,4,5,6", "1,,2,3,4,5,6"})
    void checkWrongDelimiter(String value) {
        assertThatThrownBy(() -> {
            List<Number> numbers = new ManualNumbersGenerator(value).generate();
            new Ticket(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("숫자가 아닌 경우")
    void checkNotNumber() {
        assertThatThrownBy(() -> {
            List<Number> numbers = new ManualNumbersGenerator("1,^,2,3,4,5").generate();
            new Ticket(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("숫자가 6개가 아닌 경우")
    void checkNumbersSize() {
        assertThatThrownBy(() -> {
            List<Number> numbers = new ManualNumbersGenerator("1,2,3,4,5").generate();
            new Ticket(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_SIZE);
    }

    @Test
    @DisplayName("숫자의 범위가 1부터 45사이의 수가 아닌 경우")
    void checkNumberInRange() {
        assertThatThrownBy(() -> {
            List<Number> numbers = new ManualNumbersGenerator("1,2,3,4,5,46").generate();
            new Ticket(numbers);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_RANGE);
    }

    @Test
    @DisplayName("숫자가 중복되는 경우")
    void checkDuplicated() {
        assertThatThrownBy(() -> {
            List<Number> numbers = new ManualNumbersGenerator("1,1,2,3,4,5").generate();
            new Ticket(numbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_DUPLICATED);
    }


}
