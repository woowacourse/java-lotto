package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RepeatCountTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void RepeatCount생성_확인(int repeatCount) {
        RepeatCount count = new RepeatCount(repeatCount);
        Assertions.assertThat(count)
                .isNotNull()
                .hasFieldOrPropertyWithValue("repeatCount", repeatCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void 잘못된생성_예외처리_확인(int totalRepeatCount) {
        Assertions.assertThatThrownBy(() -> new RepeatCount(totalRepeatCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("반복 횟수는 음수일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,0", "2,0,2", "3,1,2"})
    void 자동_구매수_반환_확인(int totalCount, int userCount, int expected) {
        RepeatCount count = new RepeatCount(totalCount);
        RepeatCount userRepeatCount = count.split(userCount);
        Assertions.assertThat(count).hasFieldOrPropertyWithValue("repeatCount", expected);
        Assertions.assertThat(userRepeatCount).hasFieldOrPropertyWithValue("repeatCount", userCount);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1", "2,3", "10, 20"})
    void 자동_구매수_예외_확인(int totalCount, int userCount) {
        RepeatCount count = new RepeatCount(totalCount);
        Assertions.assertThatThrownBy(() -> count.split(userCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 횟수가 총 횟수를 초과할 수 없습니다.");
    }

    @Test
    void 반복이_0인지_확인() {
        RepeatCount repeatCount1 = new RepeatCount(0);
        RepeatCount repeatCount2 = new RepeatCount(1);
        Assertions.assertThat(repeatCount1.isNotZero()).isFalse();
        Assertions.assertThat(repeatCount2.isNotZero()).isTrue();
    }
}
