package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketTest {

    @ParameterizedTest(name = "[{index}] 당첨 결과 : {3}")
    @MethodSource("provideLottoData")
    void 로또의_당첨_여부를_판단하는_기능_테스트(List<LottoNumber> inputWinningNumbers, LottoNumber inputBonusNumber,
                                List<LottoNumber> inputLottoNumbers, Rank rank) {
        WinningNumbers winningNumbers = new WinningNumbers(inputWinningNumbers, inputBonusNumber);
        LottoTicket lottoTicket = new LottoTicket(inputLottoNumbers);

        Rank resultRank = lottoTicket.compareNumbers(
                winningNumbers.getWinningNumbers(), winningNumbers.getBonusNumber());

        assertThat(resultRank).isEqualTo(rank);
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> provideLottoData() {
        List<LottoNumber> winningNumbersForTest = toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        return Stream.of(
                Arguments.of(winningNumbersForTest, LottoNumber.valueOf(7), winningNumbersForTest,
                        Rank.MATCH_SIX_NUMBERS),
                Arguments.of(winningNumbersForTest, LottoNumber.valueOf(7), toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.MATCH_FIVE_NUMBERS),
                Arguments.of(winningNumbersForTest, LottoNumber.valueOf(8), toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.MATCH_FIVE_AND_BONUS_NUMBERS)
        );
    }
}
