package lotto;

import lotto.model.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("6개의 랜덤한 로또 번호를 생성하는지 확인")
    void shuffleTest() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Integer> lotto = lottoGenerator.generateLotto();

        assertThat(lotto.size()).isEqualTo(6);
    }
}
