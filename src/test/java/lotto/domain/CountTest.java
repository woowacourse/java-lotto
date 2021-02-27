package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CountTest {

    Money money = new Money(10_000);
    Count count = new Count(money.count());

    @Test
    void total() {
        int totalCount = count.getTotalCount();
        assertThat(totalCount).isEqualTo(10);
    }

    @Test
    void manual() {
        count.manualCount(3);
        int manualCount = count.getManualCount();
        assertThat(manualCount).isEqualTo(3);
    }

    @Test
    void auto() {
        count.manualCount(3);
        int autoCount = count.getAutoCount();
        assertThat(autoCount).isEqualTo(7);
    }

}
