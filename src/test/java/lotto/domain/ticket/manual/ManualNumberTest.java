package lotto.domain.ticket.manual;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualNumberTest {

    @DisplayName("개수를 잘못 입력한 경우 exception 발생")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5|5", "1,2,3,4,5,6,7|7"}, delimiter = '|')
    void validateSize(String numberLine, int validateSize) {
        assertThatThrownBy(() -> new ManualNumber(numberLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d : 로또 번호는 6개만 허용됩니다.", validateSize);
    }

    @DisplayName("숫자가 아닌 문자를 입력할 경우 exception 발생")
    @Test
    void wrongInputThenException() {
        String wrongInput = "1,2,3,4,5,a";

        assertThatThrownBy(() -> new ManualNumber(wrongInput))
                .isInstanceOf(NumberFormatException.class);
    }
}