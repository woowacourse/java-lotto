package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

    @Test
    void 로또_개수만큼_로또를_생성한다() {
        // Given
        LottoGenerator lottoGenerator = new LottoGenerator();
        int count = 3;

        // When
        List<Lotto> lottos = lottoGenerator.generateLotto(count);

        // Then
        Assertions.assertThat(lottos.stream().distinct().count()).isEqualTo(3);
    }
}
