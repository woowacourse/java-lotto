package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LottoNumberTest {
    @Test
    void 반환한_로또_숫자_개수_확인() {
        assertThat(LottoNumber.getRandomSixNumbers().size()).isEqualTo(6);
    }

    @Test
    void 랜덤으로_숫자들이_잘_반환되는지_확인() {
        List<LottoNumber> lottoNumbers1 = LottoNumber.getRandomSixNumbers();
        List<LottoNumber> lottoNumbers2 = LottoNumber.getRandomSixNumbers();
        assertFalse(lottoNumbers1 == lottoNumbers2);
    }
}
