package lotto.domain.lotto;

import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosTest {
    private static final int price = 3000;
    private static final int count = price / PurchaseAmount.LOTTO_PRICE;

    @Test
    void 수동_구입개수_1개일_경우_전체_로또_수() {
        Lottos lottos = Lottos.of(PurchaseCount.of(PurchaseAmount.of(price), 1),
                Arrays.asList(new Numbers("1,2,3,4,5,6")));
        assertThat(lottos.getLottos().size()).isEqualTo(count);
    }

    @Test
    void 수동_구입개수_3개일_경우_전체_로또_수() {
        Lottos lottos = Lottos.of(PurchaseCount.of(PurchaseAmount.of(price), 3),
                Arrays.asList(
                        new Numbers("1,2,3,4,5,6"),
                        new Numbers("1,2,3,4,5,6"),
                        new Numbers("1,2,3,4,5,6")
                ));
        assertThat(lottos.getLottos().size()).isEqualTo(count);
    }
}
