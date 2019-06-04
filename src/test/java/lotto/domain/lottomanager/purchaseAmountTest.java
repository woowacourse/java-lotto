package lotto.domain.lottomanager;

import lotto.domain.user.PurchaseAmount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class purchaseAmountTest {

    @Test
    void null_check() {
        assertThrows(NullPointerException.class, () -> {
           PurchaseAmount.createLottoAmount(null);
        });
    }

    @Test
    void 음수일_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            PurchaseAmount.createLottoAmount(-1);
        });
    }

    @Test
    void 판매_단위가_아닌경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            PurchaseAmount.createLottoAmount(1001);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PurchaseAmount.createLottoAmount(10010);
        });
    }

    @Test
    void 최소_1000원이상() {
        assertThrows(IllegalArgumentException.class, () -> {
            PurchaseAmount.createLottoAmount(0);
        });
    }

    @Test
    void 생성될_로또_개수() {
        assertThat(PurchaseAmount.createLottoAmount(1000).isEqualsAmount(1)).isTrue();
    }
}