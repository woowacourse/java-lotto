package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {

    @ParameterizedTest
    @DisplayName("당첨번호끼리/당첨번호,보너스볼 간에 중복 - 예외발생")
    @CsvSource(value = {"1,1,2,3,4,5:6", "1,2,3,4,5,6:6"}, delimiter = ':')
    void duplicatedNumbers_fail(String lottoNumbersValue, String bonusBallValue) {
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersValue, bonusBallValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("당첨번호,보너스볼이 정해진 길이가 아니거나 범위를 벗어난 수 등 올바르지 않은 입력 - 예외발생")
    @MethodSource("invalidInput_testcase")
    void invalidInput_fail(String lottoNumbersValue, String bonusBallValue) {
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersValue, bonusBallValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidInput_testcase() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", "s"),
                Arguments.of("1,2,3,4,5,s", "7"),
                Arguments.of("1,2,3,4,5", "7"),
                Arguments.of("1,2,3,4,5,6,7", "8"),
                Arguments.of("1,2,3,4,5,6", "7,8"),
                Arguments.of("1,2,3,4,5,6", "46"),
                Arguments.of("1,2,3,4,5,6", "0"),
                Arguments.of("", ""),
                Arguments.of("1", "2"),
                Arguments.of("1", ""),
                Arguments.of("", "1")
        );
    }

    @ParameterizedTest
    @DisplayName("당첨번호,보너스볼과 추첨번호 매칭해서 올바른 등수 반환")
    @MethodSource("getRank_testcase")
    void getRank(String lottoTicketValue, Rank expectedRank) {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket(lottoTicketValue);

        assertThat(winningNumbers.matchRank(lottoTicket)).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> getRank_testcase() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", Rank.FIRST_PLACE),
                Arguments.of("1,2,3,4,5,7", Rank.SEC0ND_PLACE),
                Arguments.of("1,2,3,4,5,12", Rank.THIRD_PLACE),
                Arguments.of("1,2,3,4,12,13", Rank.FOURTH_PLACE),
                Arguments.of("1,2,3,12,13,14", Rank.FIFTH_PLACE),
                Arguments.of("1,2,13,14,15,16", Rank.UNRANKED),
                Arguments.of("11,12,13,14,15,16", Rank.UNRANKED)
        );
    }
}