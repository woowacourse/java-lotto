package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lottogenerator.RandomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {
    @DisplayName("구입 개수만큼 로또를 생성한다")
    @Test
    void lottosCreationTest() {
        int quantity = 7;
        Lottos lottos = Lottos.ofSize(quantity, new RandomLottoGenerator());

        // TODO: 사이즈 체크하기
        assertThat(lottos).isNotNull()
                .isInstanceOf(Lottos.class);
    }
}
