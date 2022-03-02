package lotto.model.generator;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoLottoGeneratorTest {

    private LottoGenerator autoLottoGenerator = new AutoLottoGenerator();

    @DisplayName("자동로또 5개 생성 테스트")
    @Test
    void autoLottoGeneratorTest() {
        int lottoCount = 5;
        Lottos lottos = autoLottoGenerator.generateLottos(lottoCount, 1, 45, 6);
        int expected = lottos.size();
        assertThat(lottoCount).isEqualTo(expected);
    }
}
