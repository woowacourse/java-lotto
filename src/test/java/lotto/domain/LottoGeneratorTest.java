package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("로또 생성 시 6개의 숫자가 생성되는지 확인")
    void lottoCrateTest() {
        Lotto lotto = LottoGenerator.createLotto();
        assertThat(lotto.getNumbers().size()).isEqualTo(6);
    }
}