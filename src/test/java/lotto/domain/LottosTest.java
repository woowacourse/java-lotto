package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {
    @Test
    @DisplayName("구매할 갯수를 올바르게 구하는지")
    void Calculate_Lotto_Count() {
        int unitPrice = 1000;
        Lottos lottos = new Lottos(new Money("10000", unitPrice), unitPrice);
        assertThat(lottos.getLottosSize()).isEqualTo(10);
    }
}
