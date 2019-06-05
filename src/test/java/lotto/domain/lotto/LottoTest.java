package lotto.domain.lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidCountOfLottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

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
        List<LottoNumber> lottoNumbers = Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6));

        Lotto lotto = new Lotto(lottoNumbers);

        List<LottoNumber> lottoNumbers1 = Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(7),
                LottoNumber.getNumber(8),
                LottoNumber.getNumber(9));

        assertThat(lotto.countMatchedLottoNumber(new Lotto(lottoNumbers1))).isEqualTo(3);
    }
}
