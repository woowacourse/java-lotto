package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;


@SuppressWarnings("NonAsciiCharacters")
class WinningNumbersTest {

    private static List<LottoNumber> toLottoNumbers(List<Integer> integers) {
        return integers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    @Test
    void 보너스_볼_숫자가_지난_주_당첨_번호와_중복인_경우() {
        assertThatThrownBy(
                () -> new WinningNumbers(toLottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(1)))
                .hasMessageContaining("지난주 당첨 번호와 중복되는 숫자입니다.")
                .isInstanceOf(RuntimeException.class);
    }
}
