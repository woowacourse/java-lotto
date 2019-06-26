package lotto.domain.lotto;

import lotto.domain.exception.DuplicateLottoNumberException;
import lotto.domain.exception.InvalidCountOfLottoNumberException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    void 생성자_확인_길이가_6개_인지() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(lottoNumbers, false)).isInstanceOf(InvalidCountOfLottoNumberException.class);
    }

    @Test
    void 생성자_확인_중복되는_숫자가_있는_경우() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> new Lotto(lottoNumbers, false)).isInstanceOf(DuplicateLottoNumberException.class);
    }

    @Test
    void 당첨번호와_일치하는_로또번호의_개수() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(lottoNumbers, false);

        List<Integer> lottoNumbers1 = Arrays.asList(1, 2, 3, 7, 8, 9);

        assertThat(lotto.countMatchedLottoNumber(new Lotto(lottoNumbers1, false))).isEqualTo(3);
    }
}
