package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.LottoNumberAutoStrategy;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumberAutoGeneratorTest {

    @Test
    void 로또번호를_6개_생성하는_기능() {
        assertThat(LottoNumberAutoStrategy.generateAutoLottoNumber(1).getLottoNumbers().get(0).size())
                .isEqualTo(6);
    }
}
