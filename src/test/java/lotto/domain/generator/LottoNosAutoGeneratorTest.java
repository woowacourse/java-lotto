package lotto.domain.generator;

import lotto.domain.Lotto;
import lotto.domain.LottoNo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNosAutoGeneratorTest {
    @Test
    void 사이즈_6개인지_확인() {
        LottoNosAutoGenerator lottoNosAutoGenerator = new LottoNosAutoGenerator();
        List<LottoNo> lottoNos = lottoNosAutoGenerator.generate();
        assertThat(Lotto.LOTTO_NO_SIZE).isEqualTo(lottoNos.size());
    }
}