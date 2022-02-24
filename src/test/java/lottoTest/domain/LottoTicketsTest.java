package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.LottoTickets;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketsTest {

    @Test
    void 입력한_금액만큼_로또_개수를_생성하는_기능() {
        LottoTickets lottoTickets = new LottoTickets(14);
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(14);
    }
}
