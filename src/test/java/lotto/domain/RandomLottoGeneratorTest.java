package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {

    private RandomLottoGenerator randomLottoGenerator;

    @BeforeEach
    void setUp() {
        randomLottoGenerator = new RandomLottoGenerator();
    }

    @Test
    void 로또_개수만큼_로또를_생성한다() {
        // Given
        final int count = 3;

        // When
        final List<Lotto> lottos = randomLottoGenerator.generate(count);

        // Then
        Assertions.assertThat(lottos.stream().distinct().count()).isEqualTo(3);
    }

    @Test
    void 로또_개수가_양수가_아니면_예외가_발생한다() {
        // Given
        final int count = -1;

        // When & Then
        Assertions.assertThatThrownBy(() -> randomLottoGenerator.generate(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("생성할 로또의 개수는 양수여야합니다.");
    }

}
