package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTicketFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketFactoryTest {

    @Test
    @DisplayName("전달 된 돈 만큼의 로또 번호 라인를 가진 로또 티켓을 발행한다")
    public void create_lotto_ticket_by_sending_money(){
        LottoTicket lottoTicket = LottoTicketFactory.createLottoTicket(14000);
        assertThat(lottoTicket.getLength()).isEqualTo(14);
    }
}

