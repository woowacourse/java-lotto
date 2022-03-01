package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @Test
    @DisplayName("6개의 랜덤한 로또 번호를 생성하는지 확인")
    void shuffleTest() {
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        assertThatCode(lottoNumberGenerator::generate)
            .doesNotThrowAnyException();
    }
}
