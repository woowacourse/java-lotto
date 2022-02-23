package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lotto.LottoTicket;
import lotto.Rank;
import lotto.WinningNumbers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketTest {

    @ParameterizedTest(name = "[{index}] 로또 등수: {3}")
    @MethodSource("provideLottoData")
    void 로또의_당첨_여부를_판단하는_기능_테스트(List<Integer> winningNumber, int bonusNumber, List<Integer> lottoNumber, Rank rank) {
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);
        LottoTicket lottoTicket = new LottoTicket(lottoNumber);
        Rank lottoRank = lottoTicket.compareNumbers(winningNumbers.getWinningNumbers(),
                winningNumbers.getBonusNumber());
        assertThat(lottoRank).isEqualTo(rank);
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
