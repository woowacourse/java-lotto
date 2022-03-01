package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("구입금액 만큼 로또티켓을 구입")
    void buyLottosByAuto() {
        Lottos lottos = Lottos.buyLottosByAuto(new Money(10000));
        assertThat(lottos.getLottos().size()).isEqualTo(10);
    }
}
