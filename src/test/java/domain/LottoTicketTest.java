package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓 인스턴스 생성 테스트")
    public void createLottoTicket() {
        int lottoCount = 14;
        LottoTicket lottoTicket = new LottoTicket(lottoCount, new AlwaysSameSixNumberGenerator());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }
}
