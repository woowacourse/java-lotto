package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
    @Test
    void 수익률_10000원으로_50000원_번경우() {
        Map<Prize, Integer> result = new HashMap<>();
        result.put(Prize.THIRD, 1);
        int buyPrice = 10000;
        assertThat(new Result(result).calculateRateOfReturn(buyPrice)).isEqualTo(500.0);
    }
}
