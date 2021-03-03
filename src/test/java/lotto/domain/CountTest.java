package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CountTest {

    private static final String NUMBER_EXCEED_ERROR = "[ERROR] 구매 가능한 수를 초과했습니다.";

    @Test
    void count() {
        Count count = new Count(10);
        int totalCount = count.getCount();
        assertThat(totalCount).isEqualTo(10);
    }

    @Test
    void validateNegative() {
        assertThatThrownBy(() -> {
            new Count(-1);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NUMBER_EXCEED_ERROR);
    }
}
