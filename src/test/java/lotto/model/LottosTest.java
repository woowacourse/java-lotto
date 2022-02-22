package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class LottosTest {

    @Test
    void 저장_테스트() {
        Lotto lotto = new Lotto(Arrays.asList(1,2,3,4,5,6));
        Lottos lottos = new Lottos();
        lottos.insert(lotto);
        assertThat(lottos.getLottos()).contains(lotto);
    }
}
