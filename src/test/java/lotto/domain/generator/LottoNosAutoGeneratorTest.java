package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNosAutoGeneratorTest {
    @Test
    void 사이즈_6개인지_확인() {
        LottoNosAutoGenerator lottoNosAutoGenerator = new LottoNosAutoGenerator();
        List<LottoNo> lottoNos = lottoNosAutoGenerator.generate();
        assertThat(Lotto.LOTTO_NO_SIZE).isEqualTo(lottoNos.size());
    }

    @Test
    void 제대로_리턴하는지_확인() {
        List<LottoNo> actual = new LottoNosManualGenerator("1,2,3,4,5,6").generate();
        List<LottoNo> expected = new LottoNosAutoGenerator((lottoNos) -> actual).generate();
        assertThat(actual).isEqualTo(expected);
    }
}