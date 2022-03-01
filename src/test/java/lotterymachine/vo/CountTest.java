package lotterymachine.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountTest {
    @Test
    @DisplayName("개수를 1만큼 증가시킨다.")
    void increase() {
        Count count = Count.from(1);
        count.increase();
        Count expected = Count.from(2);
        assertThat(count).isEqualTo(expected);
    }
}
