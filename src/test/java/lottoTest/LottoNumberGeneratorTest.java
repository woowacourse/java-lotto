package lottoTest;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.LottoNumberGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberGeneratorTest {

    @Test
    void 로또번호를_6개_생성하는_기능() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        assertThat(lottoNumberGenerator.getLottoNumbers(6).size()).isEqualTo(6);
    }
}
