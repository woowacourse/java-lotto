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

        assertThat(lottos).isEqualTo(new Lottos(Arrays.asList(lotto, lotto1)));
    }

    @Test
    void 다른_Lottos를_추가() {
        LottoNumbers numbers = LottoNumbersGenerator.getLottoNumbers();
        LottoNumbers numbers1 = LottoNumbersGenerator.getLottoNumbers();

        Lotto lotto = new Lotto(numbers);
        Lotto lotto1 = new Lotto(numbers1);

        Lottos lottos = new Lottos();
        lottos.add(numbers);
        Lottos lottos1 = new Lottos();
        lottos1.add(numbers1);
        lottos.addAll(lottos1);

        assertThat(lottos).isEqualTo(new Lottos(Arrays.asList(lotto, lotto1)));
    }
}
