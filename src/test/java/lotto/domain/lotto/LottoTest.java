package lotto.domain.lotto;

import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidCountOfLottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void 생성자_확인_로또숫자가_null_인_경우() {
        assertThatThrownBy(() -> new Lotto(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void 생성자_확인_로또숫자가_비어있을_경우() {
        assertThatThrownBy(() -> new Lotto(Collections.emptyList())).isInstanceOf(NullPointerException.class);
    }

    @Test
    void 생성자_확인_길이가_6개_인지() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5));

        assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(InvalidCountOfLottoNumberException.class);
    }

    @Test
    void 생성자_확인_중복되는_숫자가_있는_경우() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(5));

        assertThatThrownBy(() -> new Lotto(lottoNumbers)).isInstanceOf(DuplicateLottoNumberException.class);
    }

    @Test
    void 당첨번호와_일치하는_로또번호의_개수() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6)));

        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(7));


        assertThat(lotto.match(new Lotto(lottoNumbers))).isEqualTo(5);
    }

    @Test
    void 보너스숫자와_일치하는지_확인() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6)));

        assertThat(lotto.contains(LottoNumber.getNumber(6))).isTrue();
    }

    @Test
    void 보너스숫자와_일치하지_않는지_확인() {
        Lotto lotto = new Lotto(Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6)));

        assertThat(lotto.contains(LottoNumber.getNumber(7))).isFalse();
    }
}
