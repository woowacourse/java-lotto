package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTicketTest {
    @DisplayName("WinningLottoTicket 생성자 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", "1, 2, 3, 4, 5, f", "1, 2, 3, 4, 5     , ", "0, 2, 3, 4, 5, 46"})
    void winningLottoTicketConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new WinningLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
