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
    void 로또의_번호가_6개가_아닌_경우_테스트() {
        assertThatThrownBy(() -> new LottoTicket(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5))))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("로또 번호의 개수는")
                .hasMessageContaining("개 이어야 합니다.");
    }

    @Test
    void 로또의_번호가_중복된_경우_테스트() {
        assertThatThrownBy(() -> new LottoTicket(toLottoNumbers(Arrays.asList(1, 1, 2, 3, 4, 5))))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("로또 번호가 중복되었습니다.");
    }

    @ParameterizedTest(name = "[{index}] 로또 등수: {3}")
    @MethodSource("provideLottoData")
    void 로또의_당첨_여부를_판단하는_기능_테스트(List<LottoNumber> winningNumber, LottoNumber bonusNumber, List<LottoNumber> lottoNumber,
                                Rank rank) {
        WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);
        LottoTicket lottoTicket = new LottoTicket(lottoNumber);
        Rank lottoRank = lottoTicket.getRankBy(winningNumbers.getWinningNumbers(),
                winningNumbers.getBonusNumber());
        assertThat(lottoRank).isEqualTo(rank);
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> provideLottoData() {
        return Stream.of(
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        Rank.FIRST),
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.THIRD),
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(8),
                        toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 8)),
                        Rank.SECOND)
        );
    }
}
