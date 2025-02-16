package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {

    @Test
    void 로도_숫자들_중에_중복이_없다면_통과() {
        List<LottoNumber> validLottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(23),
                new LottoNumber(34),
                new LottoNumber(45)
        );

        // when
        // then
        assertThatCode(() -> new LottoNumbers(validLottoNumbers))
                .doesNotThrowAnyException();

    }

    @Test
    void 로또_숫자들_중에_중복이_포함되어있으면_예외_발생() {
        // given
        List<LottoNumber> inValidLottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(1),
                new LottoNumber(12),
                new LottoNumber(23),
                new LottoNumber(34),
                new LottoNumber(45)
        );

        // when
        // then
        assertThatThrownBy(() -> new LottoNumbers(inValidLottoNumbers))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 로또의_정렬이_되어있지_않다면_자동으로_정렬보정을_해준다() {
        // given
        List<LottoNumber> unsortedLottoNumbers = Arrays.asList(
                new LottoNumber(23),
                new LottoNumber(45),
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(12),
                new LottoNumber(34)
        );

        // when
        final LottoNumbers lottoNumbers = new LottoNumbers(unsortedLottoNumbers);

        List<LottoNumber> sortedLottoNumbers = unsortedLottoNumbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::getNumber))
                .toList();

        // then
        assertThat(lottoNumbers.getLottoNumbers()).isEqualTo(sortedLottoNumbers);
    }
}
