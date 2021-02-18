package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningNumbersTest {

    @ParameterizedTest
    @MethodSource("provideDuplicatedNumbersTestCase")
    void fail_duplicatedNumbers(String lottoNumbersValue, String bonusBallValue) {
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersValue, bonusBallValue))
                .isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> provideDuplicatedNumbersTestCase() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", "6"),
                Arguments.of("1,2,3,4,6,6", "7")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidInputTestCase")
    void fail_invalidInput(String lottoNumbersValue, String bonusBallValue) {
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbersValue, bonusBallValue))
                .isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> provideInvalidInputTestCase() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", "s"),
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
    @MethodSource("provideGetRankTestCase")
    void getRank(String lottoTicketValue, Rank expectedRank) {
        WinningNumbers winningNumbers = new WinningNumbers("1,2,3,4,5,6", "7");
        LottoTicket lottoTicket = new LottoTicket(lottoTicketValue);

        assertThat(winningNumbers.getRank(lottoTicket)).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> provideGetRankTestCase() {
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