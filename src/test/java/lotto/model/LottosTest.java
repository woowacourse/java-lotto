package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {
    @Test
    @DisplayName("3개의 로또 생성하는지 확인")
    void GenerateLottos() {
        Lottos lottos = Lottos.generateAuto(3);

        assertThat(lottos.getLottos().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("자동 로또와 수동 로또를 더한 총 리스트를 반환하는지 확인")
    void getTotalLottosTest() {
        Lottos auto = Lottos.generateAuto(3);
        Lottos manual = Lottos.generateAuto(2);

        Lottos lottos = Lottos.generate(auto, manual);

        assertThat(lottos.getLottos().size()).isEqualTo(5);
    }
}
