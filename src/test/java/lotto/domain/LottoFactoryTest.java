package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoFactoryTest {
    private Lottos lottos;
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lottos = new Lottos();
        lotto = new Lotto(1, 2, 3, 4, 5, 6);
    }

    @Test
    void createManualLottos() {
        lottos = LottoFactory.createManualLottos(lottos, Arrays.asList(lotto));
        assertThat(lottos.toList())
                .contains(new Lotto(1, 2, 3, 4, 5, 6));
    }

    @Test
    void createAutoLottos() {
        Purchase purchase = new Purchase(new Money(2000), 0);
        lottos = LottoFactory.createAutoLottos(lottos, purchase);
        assertThat(lottos.toList().size()).isEqualTo(2);
    }
}
