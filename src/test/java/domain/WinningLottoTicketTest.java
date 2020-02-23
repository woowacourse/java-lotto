package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private static Stream<Arguments> LottoTicketSetUp() {
        return Stream.of(
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6)), 6),
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 8)), 5),
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 8, 9)), 4),
                Arguments.of(new ArrayList(Arrays.asList(1, 2, 3, 8, 9, 10)), 3)
        );
    }

    @DisplayName("당첨 복권 번호와 얼마나 맞는지 카운트해주는 메서드 테스트")
    @ParameterizedTest
    @MethodSource("LottoTicketSetUp")
    void winningLottoCount(List<Integer> originalLottoTicket, int expected) {
        LottoTicket lottoTicket = new LottoTicket(originalLottoTicket);
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6", "7");
        Assertions.assertThat(winningLottoTicket.getCorrectCount(lottoTicket)).isEqualTo(expected);
    }
}
