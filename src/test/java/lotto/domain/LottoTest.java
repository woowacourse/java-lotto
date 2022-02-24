package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    @DisplayName("구매할 갯수를 올바르게 구하는지")
    void Calculate_Lotto_Count() {
        Lotto lotto = new Lotto(new Money("10000"));
        assertThat(lotto.getLottoSize()).isEqualTo(10);
    }
}
