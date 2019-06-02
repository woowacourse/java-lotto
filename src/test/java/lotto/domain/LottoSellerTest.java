package lotto.domain;

import lotto.domain.exception.InvalidLottoPriceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoSellerTest {
    @Test
    void 최소_구매가격을_충족하는_숫자가_아닌_경우() {
        assertThrows(InvalidLottoPriceException.class, () -> {
            new LottoSeller(new Cash(999));
        });
    }

    @Test
    void 구매가격에_맞는_장_수를_알려주는_메소드() {
        Cash purchasePrice = new Cash(3550);
        assertThat(new LottoSeller(purchasePrice).getNumOfLotto()).isEqualTo(3);
    }

    @Test
    void 잔돈을_구하는_메소드() {
        Cash purchasePrice = new Cash(3550);
        assertThat(new LottoSeller(purchasePrice).getChange()).isEqualTo(550);
    }
}