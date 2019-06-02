package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @Test
    void 추가() {
        Lotto lotto = new Lotto(LottoNumbersGenerator.getLottoNumbers());
        Lotto lotto1 = new Lotto(LottoNumbersGenerator.getLottoNumbers());

        Lottos lottos = new Lottos();
        lottos.add(lotto);
        lottos.add(lotto1);

        assertThat(lottos).isEqualTo(new Lottos(Arrays.asList(lotto, lotto1)));
    }

    @Test
    void 다른_Lottos를_추가() {
        Lotto lotto = new Lotto(LottoNumbersGenerator.getLottoNumbers());
        Lotto lotto1 = new Lotto(LottoNumbersGenerator.getLottoNumbers());

        Lottos lottos = new Lottos();
        lottos.add(lotto);
        Lottos lottos1 = new Lottos();
        lottos1.add(lotto1);
        lottos.addAll(lottos1);

        assertThat(lottos).isEqualTo(new Lottos(Arrays.asList(lotto, lotto1)));
    }
}
