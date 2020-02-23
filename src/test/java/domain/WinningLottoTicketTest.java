package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
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

    @DisplayName("로또 당첨 티켓 생성자 유효성 테스트")
    @ParameterizedTest
    @MethodSource("winningNumberSetUp")
    void winningNumberConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new WinningLottoTicket(input, "7");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 티켓 안에 보너스볼이 있는지 테스트")
    @Test
    void isMatchBonusBallTest() {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6", "7");

        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7));

        Assertions.assertThat(winningLottoTicket.isMatchBonusBall(lottoTicket)).isTrue();
    }
}
