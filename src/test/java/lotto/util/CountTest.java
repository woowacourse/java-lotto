package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CountTest {
    @Test
    void Count_횟수를_음수로_하려고_할_때() {
        assertThatThrownBy(() -> {
            new Count(-3);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("횟수 또는 갯수는 음수일 수 없습니다.");
    }

    @Test
    void Count_횟수_0_생성해보기 () {
        assertThat(new Count(0)).isInstanceOf(Count.class);
    }

    @Test
    void Count_양의정수_횟수_생성해보기 () {
        assertThat(new Count(100011)).isInstanceOf(Count.class);
    }
}
