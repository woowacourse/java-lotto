package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.LottoTickets;
import lotto.domain.lottonumbergenerator.LottoNumberAutoGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketsTest {

    @Test
    void 입력한_금액만큼_자동_로또를_생성하는_기능() {
        LottoTickets lottoTickets = new LottoTickets(new LottoNumberAutoGenerator(), 14);
        assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(14);
    }
}
