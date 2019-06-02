package lotto.domain;

import lotto.domain.exception.InvalidLottoPriceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoPurchasePriceTest {
    @Test
    void 같은_정수로_구매가격_객체_생성_후_비교하기() {
        assertThat(new LottoPurchasePrice(3000)).isEqualTo(new LottoPurchasePrice(3000));
    }

    @Test
    void 최소_구매가격을_충족하는_숫자가_아닌_경우() {
        assertThrows(InvalidLottoPriceException.class, () -> {
            new LottoPurchasePrice(999);
        });
    }
}