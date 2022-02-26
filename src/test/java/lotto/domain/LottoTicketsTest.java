package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.generator.AutoLottoTicketGenerator;
import lotto.domain.generator.LottoTicketGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTicketsTest {

    @DisplayName("로또 티켓의 갯수를 반환한다.")
    @Test
    void 로또_티켓_개수_확인() {
        // given
        int lottoCount = 14;
        LottoTicketGenerator lottoTicketGenerator = new AutoLottoTicketGenerator();

        // when
        LottoTickets lottoTickets = new LottoTickets(lottoCount, lottoTicketGenerator);

        // then
        assertThat(lottoTickets.totalCount()).isEqualTo(lottoCount);
    }
}