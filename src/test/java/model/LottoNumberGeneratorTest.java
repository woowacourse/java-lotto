package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGeneratorTest {

    @DisplayName("로또 번호 생성 테스트")
    @Test
    void lottoGenerateTest() {
        Set<Integer> lotto = new Lotto(new LottoNumberGenerator()).getLotto();
        assertThat(lotto.size()).isEqualTo(6);
        for (int number : lotto) {
            assertThat(number).isBetween(1, 45);
        }
    }

}