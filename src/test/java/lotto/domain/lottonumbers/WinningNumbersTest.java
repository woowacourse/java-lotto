package lotto.domain.lottonumbers;

import static lotto.domain.lottonumbers.WinningNumbers.WINNING_NUMBERS_CONTAIN_BONUS_BALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningNumbersTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1, 2, 3, 4, 5, 6  ::  30  ::  1, 2, 3, 4, 5, 7  ::  5",
            "1, 2, 3, 4, 5, 6  ::  30  ::  7, 8, 9, 10, 11, 12  ::  0"
    }, delimiterString = "  ::  ")
    @DisplayName("countContaining 은 로또 티켓의 당첨 번호 개수를 반환한다")
    void returnWinningNumberCount(
            String winningNumbersString,
            String bonusBallString,
            String ticketNumbersString,
            int expected
    ) {
        // given
        Set<String> winnings = Arrays
                .stream(winningNumbersString.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toSet());

        WinningNumbers winningNumbers = WinningNumbers.of(winnings, bonusBallString);

        Set<LottoNumber> lottoNumbers = Arrays.stream(ticketNumbersString.split(","))
                .map(String::trim)
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // when
        int count = winningNumbers.countContaining(lottoTicket);

        // then
        assertThat(count).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 2, 3, 4, 5, 6  ::  30  ::  1, 2, 3, 4, 5, 30  ::  true",
            "1, 2, 3, 4, 5, 6  ::  30  ::  1, 2, 3, 4, 5, 29  ::  false"
    }, delimiterString = "  ::  ")
    @DisplayName("보너스 볼을 포함하면 true를 반환한다")
    void bonusBallContaining(
            String winningNumbersString,
            String bonusBallString,
            String ticketNumbersString,
            boolean expected
    ) {
        // given
        Set<String> winnings = Arrays
                .stream(winningNumbersString.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toSet());

        WinningNumbers winningNumbers = WinningNumbers.of(winnings, bonusBallString);

        Set<LottoNumber> lottoNumbers = Arrays.stream(ticketNumbersString.split(",", -1))
                .map(String::trim)
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
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
        Set<String> winnings = IntStream.rangeClosed(1, 6)
                .mapToObj(String::valueOf)
                .collect(Collectors.toSet());

        String bonusBallString = "1";

        // when & then
        assertThatThrownBy(() -> WinningNumbers.of(winnings, bonusBallString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBERS_CONTAIN_BONUS_BALL);
    }
}
