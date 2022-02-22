import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeTest {

    @Test
    @DisplayName("로또 상품금 더하기 테스트")
    void addPrizeTest() {
        Prize prize = Prize.ZERO_PRIZE;
        Prize actualPrize = prize.add(new Prize(2_000_000_000));
        assertThat(actualPrize).isEqualTo(new Prize(2_000_000_000));
    }

}
