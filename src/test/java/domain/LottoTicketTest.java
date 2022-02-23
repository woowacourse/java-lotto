package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import util.ShuffleNumberGenerator;

public class LottoTicketTest {

    @Test
    public void createLottoTicket() {
        int lottoCount = 14;
        LottoTicket lottoTicket = new LottoTicket(lottoCount, new ShuffleNumberGenerator());
        assertThat(lottoTicket).isInstanceOf(LottoTicket.class);
    }
}
