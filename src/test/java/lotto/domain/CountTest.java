package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CountTest {

    public static final String NUMBER_EXCEED_ERROR = "[ERROR] 구매 가능한 수를 초과했습니다.";
    Money money = new Money("10000");
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
    void validManual() {
        assertThatThrownBy(() -> {
            count.manualCount(11);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_EXCEED_ERROR);
    }

    @Test
    void auto() {
        count.manualCount(3);
        int autoCount = count.getAutoCount();
        assertThat(autoCount).isEqualTo(7);
    }
}
