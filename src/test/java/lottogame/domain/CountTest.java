package lottogame.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountTest {

    @Test
    @DisplayName("Count 생성 테스트")
    void testCountCreate() {
        Count count = new Count(3);
        assertThat(count).isEqualTo(new Count(3));
    }

    @Test
    @DisplayName("남은 Count 줄이기 기능 테스트")
    void testCountRemain() {
        Count count = new Count(3);
        int testCount = 0;
        while (count.isRemain()) {
            count.reduce();
            testCount += 1;
        }
        assertThat(count.isRemain()).isFalse();
        assertThat(testCount).isEqualTo(3);
    }
}
