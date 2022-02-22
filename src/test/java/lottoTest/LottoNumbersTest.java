package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.LottoNumbers;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumbersTest {

    @Test
    void 로또번호를_6개_생성하는_기능() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        assertThat(lottoNumbers.getLottoNumbers(6).size()).isEqualTo(6);
    }
}
