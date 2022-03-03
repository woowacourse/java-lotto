package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.LottoNumberAutoStrategy;
import lotto.domain.LottoTickets;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketsTest {

    @Test
    void 구입한_로또_개수만큼_로또를_생성하는_기능() {
        LottoTickets lottoTickets = LottoTickets.create(LottoNumberAutoStrategy.generateAutoLottoNumber(14));
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(14);
    }
}
