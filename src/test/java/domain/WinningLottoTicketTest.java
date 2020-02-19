package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WinningLottoTicketTest {

    private static Stream<String> winningNumberSetUp() {
        return Stream.of(
                "1, 2, 3, 4, 5, f",
                "1, 2, 3, 4, 5     , ",
                null,
                ""
        );
    }

    @ParameterizedTest
    @MethodSource("winningNumberSetUp")
    void winningNumberConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new WinningLottoTicket(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
