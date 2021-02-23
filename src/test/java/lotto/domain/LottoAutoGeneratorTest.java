package lotto.domain;

import java.util.List;
import lotto.controller.generator.LottoAutoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoAutoGeneratorTest {

    @Test
    @DisplayName("로또 번호 6개 추출 테스트")
    void selectSixNumberTest() {
        int expectedNumberCount = 6;
        LottoAutoGenerator lottoAutoGenerator = new LottoAutoGenerator();
        List<Integer> generatedNumbers = lottoAutoGenerator.generateNumbers();
        assertThat(generatedNumbers.size()).isEqualTo(expectedNumberCount);
    }
}
