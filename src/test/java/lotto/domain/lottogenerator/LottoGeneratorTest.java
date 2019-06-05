package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @Test
    void 로또_생성_확인() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))))
                .isEqualTo(new Lotto(lottoNumbers));
    }
}