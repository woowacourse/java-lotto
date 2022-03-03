package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketTest {

    @Test
    void 로또_번호가_서로_중복인_경우() {
        assertThatThrownBy(
                () -> new LottoTicket(toLottoNumbers(Arrays.asList(1, 1, 3, 4, 5, 6))))
                .hasMessageContaining("입력 번호가 서로 중복되었습니다.")
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @MethodSource("provideWinningNumbers")
    void 로또_번호가_6개가_아닌_경우(List<LottoNumber> lottoNumbers) {
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .hasMessageContaining("번호는 6개를 입력해주세요.")
                .isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> provideWinningNumbers() {
        return Stream.of(
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7))),
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)))
        );
    }

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

    private static Stream<Arguments> provideLottoData() {
        List<LottoNumber> winningNumbersForTest = toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        return Stream.of(
                Arguments.of(winningNumbersForTest, LottoNumber.valueOf(7), winningNumbersForTest,
                        Rank.MATCH_SIX_NUMBERS),
                Arguments.of(winningNumbersForTest, LottoNumber.valueOf(7),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.MATCH_FIVE_NUMBERS),
                Arguments.of(winningNumbersForTest, LottoNumber.valueOf(8),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.MATCH_FIVE_AND_BONUS_NUMBERS)
        );
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }
}
