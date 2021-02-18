package lottogame.domain.lotto;

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
        int amount = 14;
        List<Lotto> lottos = LottoGenerator.makeLottos(amount);
        assertThat(lottos.size()).isEqualTo(amount);
    }
}
