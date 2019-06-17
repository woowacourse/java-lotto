package lotto.domain.lotto.LottoStrategy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ManualLottoStrategyTest {
    @Test
    void 숫자_생성_테스트() {
        LottoStrategy strategy = new ManualLottoStrategy("1,2,3");
        assertThat(strategy.generate())
                .isEqualTo(Arrays.asList(1, 2, 3));
    }

    @Test
    void 오류_테스트() {
        LottoStrategy strategy = new ManualLottoStrategy("a,b,c");
        assertThrows(NumberFormatException.class, () -> {
            strategy.generate();
        });
    }
}