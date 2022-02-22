package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @DisplayName("2000원치 로또가 2장인지 확인한다")
    @Test
    public void purchase_amount_2000() {
        Lottos lottos = new Lottos();
        lottos.purchase(2000);

        assertThat(lottos.getLottos().size()).isEqualTo(2);
    }

    @DisplayName("3000원치 로또가 3장인지 확인한다")
    @Test
    public void purchase_amount_3000() {
        Lottos lottos = new Lottos();
        lottos.purchase(3000);

        assertThat(lottos.getLottos().size()).isEqualTo(3);
    }
}
