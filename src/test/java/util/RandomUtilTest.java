package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomUtilTest {
    @RepeatedTest(100)
    @DisplayName("랜덤 값이 1~45 사이인지 확인")
    void checkRandomNumber() {
        int min = 1;
        int max = 45;
        assertThat(RandomUtil.nextInt(min, max)).isBetween(min, max);
    }
}