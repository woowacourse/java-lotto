package lotto.domain;

import lotto.domain.purchaseamount.PurchaseAmount;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoQuantityTest {
    @Test
    void 숫자_아닌_값() {
        assertThrows(InvalidLottoQuantityException.class, () -> {
            LottoQuantity.create("a");
        });
    }

    @Test
    void 음수_값() {
        assertThrows(InvalidLottoQuantityException.class, () -> {
            LottoQuantity.create(-3);
        });
    }

    @Test
    void biggerThan_숫자입력() {
        assertThat(LottoQuantity.create(3).biggerThan(1)).isTrue();
    }

    @Test
    void biggerThan_객체입력() {
        assertThat(LottoQuantity.create(3).biggerThan(LottoQuantity.create(1))).isTrue();
    }
}