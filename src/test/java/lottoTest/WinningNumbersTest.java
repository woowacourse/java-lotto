package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Stream;
import lotto.LottoTicket;
import lotto.Rank;
import lotto.WinningNumbers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class WinningNumbersTest {

    @ParameterizedTest
    @MethodSource("provideLottoData")
    void 로또의_당첨_여부를_판단하는_기능_테스트() {
        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6));
        Rank rank = winningNumbers.isWinning(lottoTicket);
        assertThat(rank).isEqualTo(Rank.MATCH_SIX_NUMBERS);
    }

    private static Stream<Arguments> provideLottoData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7, Arrays.asList(1, 2, 3, 4, 5, 6),
                        Rank.MATCH_SIX_NUMBERS),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7, Arrays.asList(1, 2, 3, 4, 5, 8),
                        Rank.MATCH_FIVE_NUMBERS),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 8, Arrays.asList(1, 2, 3, 4, 5, 8),
                        Rank.MATCH_FIVE_AND_BONUS_NUMBERS)
        );
    }
}
