package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {
    @Test
    void 여섯개_일치() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        Lotto lotto = new Lotto(lottoNumbers);

        List<LottoNumber> winningNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));
        LottoNumbers winningLottoNumbers = new LottoNumbers(winningNumbers);
        Lotto winningLotto = new Lotto(winningLottoNumbers);

        assertThat(lotto.match(winningLotto)).isEqualTo(6);
    }

    @Test
    void 불일치() {
        List<LottoNumber> numbers = Arrays.asList(
                new LottoNumber(11),
                new LottoNumber(12),
                new LottoNumber(13),
                new LottoNumber(14),
                new LottoNumber(15),
                new LottoNumber(16));
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        Lotto lotto = new Lotto(lottoNumbers);

        List<LottoNumber> winningNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6));
        LottoNumbers winningLottoNumbers = new LottoNumbers(winningNumbers);
        Lotto winningLotto = new Lotto(winningLottoNumbers);

        assertThat(lotto.match(winningLotto)).isEqualTo(0);
    }
}
