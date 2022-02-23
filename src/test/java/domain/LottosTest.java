package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("Lottos를 생성하는 경우")
    void createLottos() {
        int lottoCount = 1;
        Lottos lottos = new Lottos(lottoCount);

        assertThat(lottos).isNotNull();
    }
}
