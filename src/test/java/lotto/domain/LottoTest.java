package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTest {
    @Test
    void 주어진_가격으로_정확한_매수를_구한다() {
        Lottos lottos = new Lottos(3000);
        assertThat(lottos.getTicketCount()).isEqualTo(3);
    }
}
