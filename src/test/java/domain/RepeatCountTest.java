package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RepeatCountTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("RepeatCount생성 확인")
    void test1(int repeatCount) {
        Assertions.assertThatCode(() -> new RepeatCount(repeatCount)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    @DisplayName("음수입력 예외처리")
    void test2(int totalRepeatCount) {
        Assertions.assertThatThrownBy(() -> new RepeatCount(totalRepeatCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("반복 횟수는 음수일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,0", "2,0,2", "3,1,2"})
    @DisplayName("자동 구매수 반환 확인")
    void test3(int totalCount, int userCount, int expected) {
        RepeatCount count = new RepeatCount(totalCount);
        RepeatCount userRepeatCount = count.split(userCount);
        Assertions.assertThat(count).hasFieldOrPropertyWithValue("repeatCount", expected);
        Assertions.assertThat(userRepeatCount).hasFieldOrPropertyWithValue("repeatCount", userCount);
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1", "2,3", "10, 20"})
    @DisplayName("자동 구매수 예외 확인")
    void test4(int totalCount, int userCount) {
        RepeatCount count = new RepeatCount(totalCount);
        Assertions.assertThatThrownBy(() -> count.split(userCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 횟수가 총 횟수를 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("반복이 0인지 확인")
    void test5() {
        RepeatCount repeatCount1 = new RepeatCount(0);
        RepeatCount repeatCount2 = new RepeatCount(1);
        Assertions.assertThat(repeatCount1.isNotZero()).isFalse();
        Assertions.assertThat(repeatCount2.isNotZero()).isTrue();
    }
}
