package lotto.domain.count;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountTest {

    @Test
    @DisplayName("Count 객체 생성")
    void createCount() {
        Count count = new Count(10, 4);
        assertThat(count.getManualCounts()).isEqualTo(4);
        assertThat(count.getAutoCounts()).isEqualTo(6);
    }

    @Test
    @DisplayName("전체 로또 개수보다 많은 수동 로또 생성시 예외 발생")
    void createCountWithManualCountOverTotalCountThrowsException() {
        assertThatThrownBy(() -> new Count(1, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("전체 로또의 개수가 음수일 경우 예외 발생")
    void createCountWithNegativeTotalCountThrowsException() {
        assertThatThrownBy(() -> new Count(-1, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 로또 개수가 음수일 경우 예외 발생")
    void createCountWithNegativeManualCountThrowsException() {
        assertThatThrownBy(() -> new Count(1, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}