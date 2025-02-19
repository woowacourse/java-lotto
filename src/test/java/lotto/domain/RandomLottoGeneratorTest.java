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
    void 로또_금액을_입력으로_받아_로또를_생성한다() {
        // Given
        final int price = 3000;

        // When
        final List<Lotto> lottos = randomLottoGenerator.generate(price);

        // Then
        Assertions.assertThat(lottos.stream().distinct().count()).isEqualTo(3);
    }

    @Test
    void 로또_금액이_1000원_미만이면_예외가_발생한다() {
        // Given
        final int price = 999;

        // When & Then
        Assertions.assertThatThrownBy(() -> randomLottoGenerator.generate(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 구입 금액은 1000원 이상이어야 합니다.");
    }

    @Test
    void 구입_금액에_해당하는_로또_개수를_출력한다() {
        // Given
        final int lottoPrice = 5300;

        // When & Then
        Assertions.assertThat(randomLottoGenerator.calculateLottoCount(lottoPrice)).isEqualTo(5);
    }

}
