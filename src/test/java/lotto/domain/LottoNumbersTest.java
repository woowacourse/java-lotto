package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersTest {
    @Test
    void 여섯개의_중복없는_로또_번호가_나오는지_확인() {
        Set<LottoNumber> lottoNumbers = new HashSet<>(LottoNumbers.getLottoNumbers());

        assertThat(lottoNumbers.size()).isEqualTo(6);
    }

    @Test
    void 바뀐_결과가_나오는지_확인() {
        assertThat(LottoNumbers.getLottoNumbers().containsAll(LottoNumbers.getLottoNumbers())).isFalse();
    }
}
