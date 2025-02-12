package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    @Test
    void 주어진_가격으로_정확한_매수를_구한다() {
        Lottos lottos = new Lottos(3000);
        assertThat(lottos.getTicketCount()).isEqualTo(3);
    }

    @Test
    void 로또_티켓을_랜덤으로_생성한다() {
        Lottos lottos = new Lottos(3000);
        assertThat(lottos.getLottos().get(0).numbers.size()).isEqualTo(6);
    }
}
