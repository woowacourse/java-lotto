package lottogame.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomUtilsTest {

    @Test
    @DisplayName("지정된 범위의 난수 생성")
    void random_number_make() {
        final int startNumber = 1;
        final int endNumber = 45;

        for (int i = 0; i < 999999; i++) {
            assertThat(RandomUtils.nextPositiveInt(endNumber, endNumber))
                .isBetween(startNumber, endNumber);
        }
    }

    @Test
    @DisplayName("잘못된 범위 입력 예외처리")
    void random_number_make_exception() {
        assertThatThrownBy(() -> RandomUtils.nextPositiveInt(10, 9))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RandomUtils.nextPositiveInt(-3, 9))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
