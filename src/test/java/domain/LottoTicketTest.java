package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ShuffleNumberGenerator;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 티켓 인스턴스 생성 테스트")
    public void createLottoTicket() {
        int lottoCount = 14;
        LottoTicket lottoTicket = new LottoTicket(lottoCount, new ShuffleNumberGenerator());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }
}
