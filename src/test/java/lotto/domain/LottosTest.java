package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @Test
    void 추가() {
        LottoNumbers numbers = LottoNumbersGenerator.getLottoNumbers();
        LottoNumbers numbers1 = LottoNumbersGenerator.getLottoNumbers();
        Lotto lotto = new Lotto(numbers);
        Lotto lotto1 = new Lotto(numbers1);

        Lottos lottos = new Lottos();
        lottos.add(numbers);
        lottos.add(numbers1);

        Lottos lottos1 = new Lottos(Arrays.asList(lotto, lotto1));

        assertThat(lottos).isEqualTo(lottos1);
    }
}
