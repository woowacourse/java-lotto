package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTicketTest {
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, f", "1, 2, 3, 4, 5     , ", ""})
    void winningNumberConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new WinningLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
