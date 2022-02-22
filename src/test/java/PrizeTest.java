import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeTest {

    @Test
    @DisplayName("로또 상품금 더하기 테스트")
    void addPrizeTest() {
        Prize prize = Prize.ZERO;
        Prize actualPrize = prize.add(new Prize(2_000_000_000));
        assertThat(actualPrize).isEqualTo(new Prize(2_000_000_000));
    }

    @Test
    @DisplayName("로또 상품금 곱하기 테스트")
    void multiplyPrize() {
        Prize prize = new Prize(2);
        Prize actualPrize = prize.multiply(4);
        assertThat(actualPrize).isEqualTo(new Prize(8));
    }

}
