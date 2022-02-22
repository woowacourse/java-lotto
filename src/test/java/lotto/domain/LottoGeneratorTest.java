package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @Test
    @DisplayName("숫자 값을 입력받아, 6개의 로또 번호들을 값만큼 중복없이 생성해 반환한다.")
    void generate() {
        //given
        final int numberOfGenerating = 5;
        final LottoGenerator lottoGenerator = new LottoGenerator();
        //when
        final List<LottoNumbers> generated = lottoGenerator.generate(numberOfGenerating);
        final int actualDistinctCount = (int) generated.stream()
                .distinct()
                .count();
        //then
        assertThat(actualDistinctCount).isEqualTo(numberOfGenerating);
    }
}