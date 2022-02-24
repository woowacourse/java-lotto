package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    private Lotto lotto;
    private Lottos lottos;

    @BeforeEach
    void init() {
        lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottos = new Lottos();
    }

    @Test
    void 저장_테스트() {
        lottos.insert(lotto);
        assertThat(lottos.getLottos()).contains(lotto);
    }
}