package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.lotto.LottoMoney;
import lotto.domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    @DisplayName("금액에 따른 로또 티켓이 생성된다.")
    void testCreateLottoTicket() {
        LottoTicket lottoTicket = new LottoTicket(new LottoMoney(100000000));
        assertThat(lottoTicket.getLottoLineSize()).isEqualTo(100000);
    }

}
