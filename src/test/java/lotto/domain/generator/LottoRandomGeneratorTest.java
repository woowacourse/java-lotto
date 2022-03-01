package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRandomGeneratorTest {
    @Test
    @DisplayName("숫자 값을 입력받아, 6개의 로또 번호들을 값만큼 중복없이 생성해 반환한다.")
    void generate_Test() {
        //given
        final int numberOfGenerating = 5;
        final LottoGenerator lottoRandomGenerator = new LottoRandomGenerator();
        //when
        final List<Lotto> generated = lottoRandomGenerator.generateLottos(numberOfGenerating);
        final int actualDistinctCount = (int) generated.stream()
                .distinct()
                .count();
        //then
        assertThat(actualDistinctCount).isEqualTo(numberOfGenerating);
    }
}
