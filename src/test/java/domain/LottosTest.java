package domain;

import domain.numbergenerator.NumberGenerator;
import domain.numbergenerator.RandomNumberGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {
    @DisplayName("Lottos가 정적 팩토리 메서드로 정상적으로 생성되는지 확인한다.")
    @Test
    void Lottos_생성_테스트() {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        Lottos lottos = Lottos.of(numberGenerator, 2);
        Assertions.assertThat(lottos).isInstanceOf(Lottos.class);
    }
}