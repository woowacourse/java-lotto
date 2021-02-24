package domain;

import domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLottoMachineTest {

    @DisplayName("로또 머신에게 구매 갯수를 넘겨주면 랜덤으로 만들어진 티켓을 반환한다.")
    @Test
    void lottoTicketMakeTest() {
        //given
        int ticketCount = 3;
        RandomLottoMachine randomLottoMachine = new RandomLottoMachine();

        //when
        List<LottoTicket> lottoTickets = randomLottoMachine.makeTickets(ticketCount);

        //then
        assertThat(lottoTickets.size()).isEqualTo(ticketCount);
    }
}