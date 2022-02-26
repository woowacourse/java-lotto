package model;

import static model.WinningNumbers.WINNING_NUMBERS_CONTAIN_BONUS_BALL;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {
    @ParameterizedTest
    @MethodSource("provideTicketForCountContaining")
    @DisplayName("로또 티켓의 당첨 번호 개수를 반환한다")
    void returnWinningNumberCount(List<Integer> ticketNumbers, int expected) {
        // given
        List<Integer> winnings = Arrays.asList(1,2,3,4,5,6);
        int bonusBallNumber = 7;
        WinningNumbers winningNumbers = WinningNumbers.of(winnings, bonusBallNumber);

        List<LottoNumber> lottoNumbers = ticketNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // when
        int count = winningNumbers.countContaining(lottoTicket);

        // then
        assertThat(count).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideTicketForBonusBall")
    @DisplayName("보너스 볼을 포함하면 true 를 반환한다")
    void bonusBallContaining(List<Integer> ticketNumbers, boolean expected) {
        // given
        List<Integer> winnings = Arrays.asList(1,2,3,4,5,6);
        int bonusBallNumber = 7;
        WinningNumbers winningNumbers = WinningNumbers.of(winnings, bonusBallNumber);

        List<LottoNumber> lottoNumbers = ticketNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // when
        boolean result = winningNumbers.containBonusBall(lottoTicket);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 볼은 중복될 수 없다.")
    void winningNumbersContainBonusBall() {
        // given
        List<Integer> winnings = IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(Collectors.toList());
        int bonusBallNumber = 1;

        // then
        assertThatThrownBy(() -> WinningNumbers.of(winnings, bonusBallNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBERS_CONTAIN_BONUS_BALL);
    }

    private static Stream<Arguments> provideTicketForCountContaining() {
        return Stream.of(
                Arguments.of(Arrays.asList(1,2,3,4,5,6), 6),
                Arguments.of(Arrays.asList(7,8,9,10,11,12), 0)
        );
    }

    private static Stream<Arguments> provideTicketForBonusBall() {
        return Stream.of(
                Arguments.of(Arrays.asList(1,2,3,4,5,6), false),
                Arguments.of(Arrays.asList(7,8,9,10,11,12), true)
        );
    }
}
