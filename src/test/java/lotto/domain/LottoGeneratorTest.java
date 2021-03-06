package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class LottoGeneratorTest {
    @Test
    @DisplayName("자동로또를 잘 생성하는지 확인")
    void generateLottoNums() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        assertThatCode(lottoGenerator::generateLottoNums)
                .doesNotThrowAnyException();
    }
}
