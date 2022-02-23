package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    @DisplayName("결과값을 대조해서 등수를 정한다.(2등)")
    void compare_2등_테스트() {
        Result result = new Result(5, true);
        boolean actual = result.compare(Rank.SECOND);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("결과값을 대조해서 등수를 정한다.(3등)")
    void compare_3등_테스트() {
        Result result = new Result(5, false);
        boolean actual = result.compare(Rank.THIRD);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("결과값을 대조해서 등수를 정한다.(1등)")
    void compare_1등_테스트() {
        Result result = new Result(6, true);
        boolean actual = result.compare(Rank.FIRST);
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);

    }
}