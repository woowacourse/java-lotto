package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @Test
    @DisplayName("구입개수 확인")
    void checkLottoCount() {
        String money = "14000";
        Lottos lottos = new Lottos(money);
        assertThat(lottos.getCount()).isEqualTo(14);
    }
}

