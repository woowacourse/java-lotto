package lotto.domain.ticket.strategy;

import lotto.LottoHelper;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.manual.ManualNumberBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualLottoMachineTest {

    @DisplayName("수동으로 로또 티켓 사기")
    @Test
    void buyTickets() {
        //given
        ManualNumberBundle manualNumberBundle = new ManualNumberBundle(Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7"));
        LottoMachine manualLottoMachine = new ManualLottoMachine(2, manualNumberBundle.getManualNumbers());

        List<LottoTicket> result = Arrays.asList(
                new LottoTicket(LottoHelper.lottoNumbers(1, 2, 3, 4, 5, 6)),
                new LottoTicket(LottoHelper.lottoNumbers(2, 3, 4, 5, 6, 7))
        );

        //when
        List<LottoTicket> lottoTickets = manualLottoMachine.buyTickets();

        //then
        assertThat(lottoTickets).isEqualTo(result);
    }

    @DisplayName("수동으로 로또 티켓입력한 것과 수동 갯수가 다를경우 exception 발생")
    @Test
    void buyTicketsException() {
        ManualNumberBundle manualNumberBundle = new ManualNumberBundle(Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7"));

        assertThatThrownBy(() -> new ManualLottoMachine(3, manualNumberBundle.getManualNumbers()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동구매 티켓의 갯수(%d)와 입력받은 수동번호의 갯수(%d)가 다릅니다.", 3, 2);
    }

}