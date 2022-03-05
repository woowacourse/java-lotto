package lottoTest.domain.lottonumbergenerator;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.lottonumbergenerator.LottoNumberAutoGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberAutoGeneratorTest {

    @Test
    void 로또번호를_6개_생성하는_기능() {
        LottoNumberAutoGenerator lottoNumberAutoGenerator = new LottoNumberAutoGenerator();
        assertThat(lottoNumberAutoGenerator.getLottoNumbersBy(6).size()).isEqualTo(6);
    }
}
