package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @Test
    void 로또_생성_확인() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))))
                .isEqualTo(new Lotto(lottoNumbers, false));
    }

    @Test
    void 수동_로또_생성() {
        Lotto lotto = LottoGenerator.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isEqualTo(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), false));
    }

    @Test
    void 자동_로또_생성() {
        Lotto lotto = LottoGenerator.create(new RandomLottoGeneratingStrategy());

        assertThat(lotto).isInstanceOf(Lotto.class);
        assertThat(lotto.isAuto()).isTrue();
    }
}