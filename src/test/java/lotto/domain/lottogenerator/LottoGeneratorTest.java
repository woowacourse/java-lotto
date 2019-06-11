package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @Test
    void 로또_생성_확인() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThat(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))))
                .isEqualTo(new Lotto(lottoNumbers));
    }

    @Test
    void 수동_로또_생성() {
        Lotto lotto = LottoGenerator.create(() -> Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isEqualTo(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 자동_로또_생성() {
        Lotto lotto = LottoGenerator.create(() -> {
            List<Integer> allLottoNumbers = IntStream.rangeClosed(LottoNumber.MIN_BOUNDARY, LottoNumber.MAX_BOUNDARY)
                    .boxed()
                    .collect(Collectors.toList());

            Collections.shuffle(allLottoNumbers);

            return allLottoNumbers.stream()
                    .limit(Lotto.LOTTO_NUMBER_SIZE)
                    .sorted()
                    .collect(Collectors.toList());
        });

        assertThat(lotto).isInstanceOf(Lotto.class);
    }
}