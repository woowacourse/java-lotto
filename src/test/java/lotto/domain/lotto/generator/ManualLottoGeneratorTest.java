package lotto.domain.lotto.generator;

import lotto.domain.purchase_info.PurchaseInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ManualLottoGeneratorTest {

    @Test
    void createLottoTickets() {
        List<String> inputs = Arrays.asList("1,2,3,4,5,6","2,3,4,5,6,7");
        PurchaseInfo purchaseInfo = new PurchaseInfo(inputs, 5);
        LottoGenerator manualGenerator = new ManualLottoGenerator();

        assertThat(manualGenerator.createLottoTickets(purchaseInfo)).size().isEqualTo(2);
    }
}