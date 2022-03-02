package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoGeneratorTest {

    @DisplayName("자동 로또를 생성")
    @Test
    void generate_auto_lottos() {
        Lottos lottos = LottoGenerator.pickAutoLottos(new LottoCount(10));

        assertEquals(lottos.getLottos().size(), 10);
    }
}