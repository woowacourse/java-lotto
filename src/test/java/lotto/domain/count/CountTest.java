package lotto.domain.count;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CountTest {

    @Test
    void testCount() {
        Count count = new Count(10, 4);
        assertThat(count.getManualCounts()).isEqualTo(4);
        assertThat(count.getAutoCounts()).isEqualTo(6);
    }

    @Test
    void testException() {
        assertThatThrownBy(() -> new Count(1, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testNegativeTotalCount() {
        assertThatThrownBy(() -> new Count(-1, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testNegativeManualCount() {
        assertThatThrownBy(() -> new Count(1, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Count getCountFixture() {
        return new Count(10, 1);
    }
}