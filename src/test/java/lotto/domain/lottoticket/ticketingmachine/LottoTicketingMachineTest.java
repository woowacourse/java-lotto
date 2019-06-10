package lotto.domain.lottoticket.ticketingmachine;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketingMachineTest {
    @Test
    void LottoTickets를_올바르게_생성하는지_체크() {
        List<LottoNumber> numbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(5)
                , LottoNumberPool.valueOf(6));
        LottoTickets expected = new LottoTickets();
        for (int i = 0; i < 3; i++) {
            expected.add(new LottoTicket(numbers));
        }

        LottoNumberGenerator manualLottoNumberGenerator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoTickets actual = LottoTicketingMachine.generateLottoTickets(3, manualLottoNumberGenerator);

        assertThat(actual).isEqualTo(expected);
    }
}