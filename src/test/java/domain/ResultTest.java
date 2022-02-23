package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD

import static org.assertj.core.api.Assertions.assertThat;
=======
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)

class ResultTest {

    @Test
    @DisplayName("결과값을 대조해서 등수를 정한다.(2등)")
    void compare_2등_테스트() {
        Result result = new Result(5, true);
<<<<<<< HEAD
        boolean actual = result.isWhatRank(Rank.SECOND);
=======
        boolean actual = result.compare(Rank.SECOND);
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("결과값을 대조해서 등수를 정한다.(3등)")
    void compare_3등_테스트() {
        Result result = new Result(5, false);
<<<<<<< HEAD
        boolean actual = result.isWhatRank(Rank.THIRD);
=======
        boolean actual = result.compare(Rank.THIRD);
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("결과값을 대조해서 등수를 정한다.(1등)")
    void compare_1등_테스트() {
        Result result = new Result(6, true);
<<<<<<< HEAD
        boolean actual = result.isWhatRank(Rank.FIRST);
=======
        boolean actual = result.compare(Rank.FIRST);
>>>>>>> 20bb1bf (feat: 2,3등은 보너스볼과 일치하는 숫자의 갯수를 기준으로, 나머지 등수는 일치하는 숫자의 갯수만으로 등수를 판정하는 로직 구현)
        boolean expected = true;
        assertThat(actual).isEqualTo(expected);

    }
}