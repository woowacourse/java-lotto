package lotterymachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class YieldTest {
    @Test
    @DisplayName("총 수익과 투자 금액을 입력 받아, 수익률을 조회한다.")
    void getProfitRate() {
        Yield yield = Yield.of(1000, 100);
        Assertions.assertThat(yield.getProfitRate()).isEqualTo(10.00);
    }
}
