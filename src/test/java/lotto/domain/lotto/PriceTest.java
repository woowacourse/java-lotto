package lotto.domain.lotto;

import lotto.domain.Price;
import lotto.domain.exception.InvalidLottoPriceException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceTest {

    @Test
    void 돈이_객체에_잘들어가는지_테스트() {
        Price price = new Price("10000");
        assertThat(price).isEqualTo(new Price("10000"));
    }

    @Test
    void 돈이_1000원_단위가_아닐때() {
        assertThrows(InvalidLottoPriceException.class, () -> {
            new Price("1001");
        });
    }

    @Test
    void 돈이_양수가_아닐때() {
        assertThrows(InvalidLottoPriceException.class, () -> {
            new Price("-1000");
        });
    }

    @Test
    void 돈이_문자일때_테스트() {
        assertThrows(InvalidLottoPriceException.class, () -> {
            new Price("abcd");
        });
    }
}
