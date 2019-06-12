package lotto.domain.ticket;

import lotto.domain.machine.ManualNumbers;
import lotto.domain.machine.Purchase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTicketFactoryTest {
    @Test
    public void 수동_티켓_생성() {
        List<ManualNumbers> multipleManualNumbers = new ArrayList<>();
        multipleManualNumbers.add(ManualNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Purchase purchase = Purchase.of(10, 1, multipleManualNumbers);
        assertThat(LottoTicketFactory.of(purchase).getIdxLottoTicket(0))
                .isEqualTo(new LottoTicket(multipleManualNumbers.get(0).getManualNumbers()));
    }

    @Test
    public void 자동_티켓_생성() {
        List<ManualNumbers> multipleManualNumbers = new ArrayList<>();
        Purchase purchase = Purchase.of(10, 0, multipleManualNumbers);
        assertThat(LottoTicketFactory.of(purchase).lottoTicketsSize()).isEqualTo(10);
    }
}