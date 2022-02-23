package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    @Test
    @DisplayName("6개의 랜덤한 로또 번호를 생성하는지 확인")
    void shuffleTest() {
        Lotto lotto = Lotto.generate();

        assertThat(lotto.getSize()).isEqualTo(6);
    }
}
