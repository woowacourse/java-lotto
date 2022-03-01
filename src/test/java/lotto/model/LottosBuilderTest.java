package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosBuilderTest {
    private final int count = 10;
    private final int manualCount = 3;
    private LottosBuilder lottosBuilder;

    @BeforeEach
    void initializeLottos() {
        lottosBuilder = LottosBuilder.of(Money.from(String.valueOf(count * 1000)), String.valueOf(manualCount));
    }

    @DisplayName("10장 중 3장이 수동이면 자동 로또는 7장 만들어진다")
    @Test
    void count_10_manual_3() {
        lottosBuilder.addAutoLottos();

        assertThat(lottosBuilder.toLottos().getAutoCount()).isEqualTo(count - manualCount);
    }

    @DisplayName("수동 로또를 정한 개수만큼 다 사면 더 이상 살 수 없다")
    @Test
    void manual_not_available() {
        for (int i = 0; i < manualCount; i++) {
            lottosBuilder.addManualLotto(List.of("1", "2", "3", "4", "5", "6"));
        }

        assertThat(lottosBuilder.isManualAvailable()).isFalse();
    }
}
