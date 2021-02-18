package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    @DisplayName("로또 번호 6개 추출 테스트")
    void selectSixNumberTest() {
        int expectedNumberCount = 6;
        List<Integer> generatedNumbers = LottoGenerator.generateNumbers();
        assertThat(generatedNumbers.size()).isEqualTo(expectedNumberCount);
    }
}