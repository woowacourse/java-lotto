package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottosTest {

    @Test
    @DisplayName("로또 수를 입력받은 만큼 로또 생성")
    void create_lottos() {
        Lottos lottos = new Lottos(14);
        assertEquals(lottos.getLottos().size(), 14);
    }
}
