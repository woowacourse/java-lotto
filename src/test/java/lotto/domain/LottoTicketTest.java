package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {
    @Test
    @DisplayName("LottoTicket 추가")
    void addLottoTicket() {
        // given
        LottoTicket lottoTicket1 = LottoTicket.createAutoLottoTicket(3);
        LottoTicket lottoTicket2 = LottoTicket.createAutoLottoTicket(2);

        // when
        lottoTicket1.addLottoTicket(lottoTicket2);

        // then
        int lottoCount = lottoTicket1.getLottoTicket().size();
        assertThat(lottoCount).isEqualTo(5);
    }
}