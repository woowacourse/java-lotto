package lotto.service;

import lotto.domain.Money;
import lotto.domain.PurchaseInfo;
import lotto.domain.number.LottoNumbers;
import lotto.domain.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineServiceTest {
    private LottoMachineService lottoMachineService = new LottoMachineService(new Money(1000));

    @DisplayName("수동 및 자동 로또 티켓 구매")
    @Test
    void buyLottoTicket() {
        PurchaseInfo purchaseInfo = new PurchaseInfo(new Money(10000), lottoMachineService.getLottoPrice(), 3);
        List<LottoNumbers> manualLottoNumbersBundle = Arrays.asList(
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoNumbers(Arrays.asList(45, 44, 43, 42, 41, 40)),
                new LottoNumbers(Arrays.asList(10, 11, 12, 13, 14, 15))
        );
        LottoTickets allLottoTickets = lottoMachineService.createAllLottoTickets(purchaseInfo, manualLottoNumbersBundle);

        assertThat(allLottoTickets.size()).isEqualTo(10);
        for (int i = 0; i < manualLottoNumbersBundle.size(); i++) {
            assertThat(allLottoTickets.list().get(i).list()).containsAll(manualLottoNumbersBundle.get(i).list());
        }
    }

    @DisplayName("로또 가격 받아오는 기능")
    @Test
    void getLottoPriceTest() {
        assertThat(lottoMachineService.getLottoPrice()).isEqualTo(new Money(1000));
    }
}