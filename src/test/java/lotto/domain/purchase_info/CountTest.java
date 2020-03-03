package lotto.domain.purchase_info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CountTest {

    @Test
    @DisplayName("count는 전체 횟수와 수동 구매 횟수를 통해 생성")
    void createCount() {
        Count count = new Count(10, 4);
        assertThat(count.getManualCounts()).isEqualTo(4);
        assertThat(count.getAutoCounts()).isEqualTo(6);
    }

    @Test
    @DisplayName("count에서 수동 구매 횟수가 전체 횟수보다 많으면 예외 발생")
    void totalCountLessThanManualThrowsException() {
        assertThatThrownBy(() -> new Count(1, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("count에서 전체 구매 횟수가 음수이면 예외 발생")
    void negativeTotalCountThrowsException() {
        assertThatThrownBy(() -> new Count(-1, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("count에서 수동 구매 횟수가 음수이면 예외 발생")
    void negativeManualCountThrowsException() {
        assertThatThrownBy(() -> new Count(1, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}