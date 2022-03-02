package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import lotto.domain.generator.AutoLottoTicketGenerator;
import lotto.domain.generator.LottoTicketGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @DisplayName("로또 티켓의 갯수를 반환한다.")
    @Test
    void checkLottoCount() {
        // given
        int lottoCount = 14;

        LottoTicketGenerator lottoTicketGenerator = new AutoLottoTicketGenerator();

        LottoTickets lottoTickets = new LottoTickets(lottoCount, new ArrayList<>(0), lottoTicketGenerator);

        // when & then
        assertThat(lottoTickets.totalCount()).isEqualTo(lottoCount);
    }
}