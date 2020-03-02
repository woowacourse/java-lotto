package lotto.domain.lotto;

import lotto.domain.purchase_info.PurchaseInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {

    @Test
    void publishLottoTickets() {
        List<String> inputs = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "3,4,5,6,7,8");
        PurchaseInfo purchaseInfo = new PurchaseInfo(inputs, 5);

        LottoTickets lottoTickets = LottoFactory.publishLottoTickets(purchaseInfo);
        assertThat(lottoTickets.getLottoTickets()).size().isEqualTo(8);
    }
}