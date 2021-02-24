package lottogame.domain.lotto;

import lottogame.domain.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {
    @BeforeEach
    void setUp() {
        LottoGenerator.generate();
    }

    @Test
    void 로또_생성() {
        Quantity quantity = Quantity.ofInt(14);
        List<Lotto> lottos = LottoGenerator.makeLottos(quantity);
        assertThat(lottos.size()).isEqualTo(quantity.value());
    }
}
