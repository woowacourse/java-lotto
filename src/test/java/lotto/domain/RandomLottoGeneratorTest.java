package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {

    @Test
    void 로또_개수만큼_로또를_생성한다() {
        // Given
        final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        final int count = 3;

        // When
        final List<Lotto> lottos = randomLottoGenerator.generate(count);

        // Then
        Assertions.assertThat(lottos.stream().distinct().count()).isEqualTo(3);
    }
}
