package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    public void 로또번호_생성_검증() {
        LottoNumber lottoNumber = new LottoNumber();
        List<Integer> testValues = lottoNumber.createLottoNumbers();
        assertThat(testValues.size()).isEqualTo(Set.copyOf(testValues).size());
    }
}
