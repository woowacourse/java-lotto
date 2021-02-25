package lottogame.domain.stats;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YieldTest {
    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void of1() {
        Yield yield = Yield.of(Money.of(5000), Money.of("1000"));
        assertEquals(yield, Yield.of(Money.of(5000), Money.of("1000")));
    }

    @Test
    @DisplayName("올바른 수익률이 계산되는지 확인")
    void of2() {
        Yield yield = Yield.of(Money.of(5000), Money.of("1000"));
        assertEquals(yield.value(), (float) 5000 / 1000);
    }
}
