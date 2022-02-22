package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @DisplayName("Lottos 생성자 테스트")
    @Test
    void lottos_constructor_test() {
        Lottos lottos = new Lottos();
    }

    @DisplayName("purchase 메서드 테스트")
    @Test
    void purchase_test() {
        Lottos lottos = new Lottos();
        lottos.purchase(new Money(10000));

        assertThat(lottos.getLottos().size()).isEqualTo(10);
    }
}
