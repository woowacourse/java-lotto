package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    public void createLottoTicket() {
        int lottoCount = 14;
        LottoTicket lottoTicket = new LottoTicket(lottoCount);
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }
}
