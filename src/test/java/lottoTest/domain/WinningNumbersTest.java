package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


@SuppressWarnings("NonAsciiCharacters")
class WinningNumbersTest {

    @Test
    void 당첨번호가_서로_중복인_경우() {
        assertThatThrownBy(
                () -> new WinningNumbers(toLottoNumbers(Arrays.asList(1, 1, 3, 4, 5, 6)), new LottoNumber(7)))
                .hasMessageContaining("당첨 번호가 서로 중복되었습니다.")
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @MethodSource("provideWinningNumbers")
    void 당첨번호가_6개가_아닌_경우(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        assertThatThrownBy(() -> new WinningNumbers(winningNumbers, bonusNumber))
                .hasMessageContaining("당첨 번호는 6개를 입력해주세요.")
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 보너스_볼_숫자가_지난_주_당첨_번호와_중복인_경우() {
        assertThatThrownBy(
                () -> new WinningNumbers(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoNumber(1)))
                .hasMessageContaining("지난주 당첨 번호와 중복되는 숫자입니다.")
                .isInstanceOf(RuntimeException.class);
    }

    private static Stream<Arguments> provideWinningNumbers() {
        return Stream.of(
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7)), new LottoNumber(7)),
                Arguments.of(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5)), new LottoNumber(7))
        );
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
