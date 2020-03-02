package lotto.domain.lotto.generator;

import lotto.domain.purchase_info.PurchaseInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoGeneratorTest {

    @Test
    void createLottoTickets() {
        List<String> inputs = Arrays.asList("1,2,3,4,5,6");
        PurchaseInfo purchaseInfo = new PurchaseInfo(inputs, 10);
        LottoGenerator autoGenerator = new AutoLottoGenerator();

        assertThat(autoGenerator.createLottoTickets(purchaseInfo)).size().isEqualTo(10);
    }
}