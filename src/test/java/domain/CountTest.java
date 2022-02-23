package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CountTest {
    @DisplayName("로또 발급 횟수 생성을 확인한다.")
    @Test
    void count_create() {
        assertDoesNotThrow(() -> new Count(4));
    }

    @DisplayName("남은 로또 발급 횟수가 정상적으로 감소되는지 확인한다.")
    @Test
    void count_decrease() {
        Count count = new Count(2);
        count = count.decrease();

        assertThat(count).isEqualTo(new Count(1));
    }
}
