package lotto.domain;

import lotto.domain.generator.AutoLottoNumbersGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    AutoLottoNumbersGenerator autoLottoNumbersGenerator;

    @BeforeEach
    void setUp() {
        autoLottoNumbersGenerator = AutoLottoNumbersGenerator.getInstance();
    }

    @Test
    void 추가() {
        Lotto lotto = new Lotto(autoLottoNumbersGenerator.generate());
        Lotto lotto1 = new Lotto(autoLottoNumbersGenerator.generate());

        Lottos lottos = new Lottos();
        lottos.add(lotto);
        lottos.add(lotto1);

        assertThat(lottos).isEqualTo(new Lottos(Arrays.asList(lotto, lotto1)));
    }

    @Test
    void 다른_Lottos를_추가() {
        Lotto lotto = new Lotto(autoLottoNumbersGenerator.generate());
        Lotto lotto1 = new Lotto(autoLottoNumbersGenerator.generate());

        Lottos lottos = new Lottos();
        lottos.add(lotto);
        Lottos lottos1 = new Lottos();
        lottos1.add(lotto1);
        lottos.addAll(lottos1);

        assertThat(lottos).isEqualTo(new Lottos(Arrays.asList(lotto, lotto1)));
    }
}
