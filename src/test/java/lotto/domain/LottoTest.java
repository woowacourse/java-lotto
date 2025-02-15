package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTest {

    private List<LottoNumber> validLottoNumbers;


    @Test
    void 올바른_로또숫자들의_구성이라면_로또_객체_정상적으로_생성() {
        // given
        validLottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(5),
                new LottoNumber(12),
                new LottoNumber(23),
                new LottoNumber(34),
                new LottoNumber(45)
        );

        // when
        final Lotto lotto = new Lotto(validLottoNumbers);

        // then
        assertThat(lotto.getLottoNumbers()).isEqualTo(validLottoNumbers);
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
        assertThatThrownBy(() -> new Lotto(inValidLottoNumbers))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 로또_숫자들_중에_원하는_값이_있다면_true를_반환() {
        // given
        List<LottoNumber> validLottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(12),
                new LottoNumber(23),
                new LottoNumber(34),
                new LottoNumber(45)
        );
        LottoNumber wantedNumber = new LottoNumber(2);

        // when
        // then
        assertThat(validLottoNumbers).contains(wantedNumber);
    }
}
