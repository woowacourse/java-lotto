package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottosTest {
    @Test
    void 구입개수_확인() {
        String money = "14000";
        Lottos lottos = new Lottos(money);
        assertThat(lottos.getCount()).isEqualTo(14);
    }
}

