package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.util.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @DisplayName("로또 구입 금액만큼 로또 티켓 수 생성 확인")
    @Test
    public void lottoTicketNumber() {
        LottoMoney lottoMoney = new LottoMoney(10000);
        LottoTickets lottoTickets = new LottoTickets();

        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(lottoNumberGenerator);
        for (Lotto lottoTicket : lottoMachine.generateLottoTickets(lottoMoney)) {
            lottoTickets.addLottoTicket(lottoTicket);
        }
        assertThat(lottoTickets.getLottoTicketsCount()).isEqualTo(10);
    }
}
