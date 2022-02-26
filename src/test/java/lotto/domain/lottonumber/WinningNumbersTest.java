package lotto.domain.lottonumber;

import static lotto.domain.lottonumber.WinningNumbers.DUPLICATED_LOTTO_TICKET_AND_BONUS_BALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
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
    @DisplayName("로또 티켓의 당첨 번호에 따른 당첨 개수가 맞는지를 확인한다")
    void returnWinningNumberCount(
            String winningNumbersString,
            String bonusBallString,
            String ticketNumbersString,
            int numberWinningsExpected
    ) {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersString, bonusBallString);

        Set<LottoNumber> lottoNumbers = Arrays.stream(ticketNumbersString.split(","))
                .map(String::trim)
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // when
        int numberWinningsActual = winningNumbers.getMatchCount(lottoTicket);

        // then
        assertThat(numberWinningsActual).isEqualTo(numberWinningsExpected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 2, 3, 4, 5, 6  ::  30  ::  1, 2, 3, 4, 5, 30  ::  true",
            "1, 2, 3, 4, 5, 6  ::  30  ::  1, 2, 3, 4, 5, 29  ::  false"
    }, delimiterString = "  ::  ")
    @DisplayName("보너스 볼을 포함할 경우 true 를 반환한다")
    void bonusBallContaining(
            String winningNumbersString,
            String bonusBallString,
            String ticketNumbersString,
            boolean expected
    ) {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersString, bonusBallString);

        Set<LottoNumber> lottoNumbers = Arrays.stream(ticketNumbersString.split(",", -1))
                .map(String::trim)
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        // when
        boolean result = winningNumbers.doesMatchBonusBall(lottoTicket);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("당첨 번호와 보너스 볼은 중복될 수 없다.")
    void winningNumbersContainBonusBall() {
        // given & when & then
        assertThatThrownBy(
                () -> new WinningNumbers("1, 2, 3, 4, 5, 6", "6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DUPLICATED_LOTTO_TICKET_AND_BONUS_BALL);

        Assertions.assertDoesNotThrow(
                ()-> new WinningNumbers("1, 2, 3, 4, 5, 16", "6")
        );
    }
}
