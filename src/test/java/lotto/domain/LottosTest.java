package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또 수를 입력받은 만큼 로또 생성")
    void create_lottos_count() {
        Lottos lottos = new Lottos(14);
        assertEquals(lottos.getLottos().size(), 14);
    }

    @Test
    @DisplayName("돈을 입력받은 만큼 로또 생성")
    void create_lottos_money() {
        Lottos lottos = new Lottos(new Money(3650));
        assertEquals(lottos.getLottos().size(), 3);
    }
}
