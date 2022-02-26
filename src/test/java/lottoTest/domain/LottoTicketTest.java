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

    @ParameterizedTest(name = "[{index}] 로또 등수: {3}")
    @MethodSource("provideLottoData")
    void 로또의_당첨_여부를_판단하는_기능_테스트(List<LottoNumber> winningNumber, LottoNumber bonusNumber, List<LottoNumber> lottoNumber,
                                Rank rank) {
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);
        LottoTicket lottoTicket = new LottoTicket(lottoNumber);
        Rank lottoRank = lottoTicket.compareNumbers(winningNumbers.getWinningNumbers(),
                winningNumbers.getBonusNumber());
        assertThat(lottoRank).isEqualTo(rank);
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> provideLottoData() {
        return Stream.of(
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(7),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        Rank.FIRST),
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(7),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.THIRD),
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(8),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.SECOND)
        );
    }
}
