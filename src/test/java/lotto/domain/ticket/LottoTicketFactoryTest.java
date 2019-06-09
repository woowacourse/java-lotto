package lotto.domain.ticket;

import lotto.domain.machine.Purchase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTicketFactoryTest {
    @Test
    public void 수동_티켓_생성() {
        Map<LottoType, Integer> autoManualNumsInformation = new HashMap<>();
        autoManualNumsInformation.put(LottoType.AUTOMATIC, 0);
        autoManualNumsInformation.put(LottoType.MANUAL, 2);
        List<List<Integer>> manualNumbers = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6));
        Purchase purchaseInformation = Purchase.of(autoManualNumsInformation, manualNumbers);
        assertThat(LottoTicketFactory.of(purchaseInformation).getIdxLottoTicket(0))
                .isEqualTo(LottoTicket.of(manualNumbers.get(0).stream().map(LottoNumber::of).collect(Collectors.toList())));
    }

    @Test
    public void 자동_티켓_생성() {
        Map<LottoType, Integer> autoManualNumsInformation = new HashMap<>();
        autoManualNumsInformation.put(LottoType.AUTOMATIC, 5);
        autoManualNumsInformation.put(LottoType.MANUAL, 0);
        List<List<Integer>> manualNumbers = Arrays.asList();
        Purchase purchaseInformation = Purchase.of(autoManualNumsInformation, manualNumbers);
        assertThat(LottoTicketFactory.of(purchaseInformation).lottoTicketsSize()).isEqualTo(5);
    }
}