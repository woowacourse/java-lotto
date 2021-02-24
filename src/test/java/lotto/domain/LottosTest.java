package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    private static Lottos lottos;

    @BeforeEach
    void setUp() {
        Money money = new Money("14000");
        String manualCount = "3";
        lottos = new Lottos(money, manualCount);
    }

    @Test
    @DisplayName("수동로또 구입개수 확인")
    void checkManualCount() {
        assertThat(lottos.getManualCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("자동로또 구입개수 확인")
    void checkRandomCount() {
        assertThat(lottos.getRandomCount()).isEqualTo(11);
    }
}

