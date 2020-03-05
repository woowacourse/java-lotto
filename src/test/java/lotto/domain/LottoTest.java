package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    @DisplayName("수동 로또 생성 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6", "43, 23, 36, 12, 10, 05"})
    void makeLotto(int one, int two, int three, int four, int five, int six) {
        Lotto lotto = Lotto
            .of(one + ", " + two + ", " + three + ", " + four + ", " + five + ", " + six);
        assertThat(lotto.contains(Ball.of(two))).isTrue();
        assertThat(lotto.contains(Ball.of(one))).isTrue();
        assertThat(lotto.contains(Ball.of(three))).isTrue();
        assertThat(lotto.contains(Ball.of(four))).isTrue();
        assertThat(lotto.contains(Ball.of(five))).isTrue();
        assertThat(lotto.contains(Ball.of(six))).isTrue();
    }

    @DisplayName("수동 로또 생성 실패 - 개수가 적절하지 않음")
    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6, 7", "1, 2, 3, 4, 5"})
    void differentCount(String string) {
        assertThatThrownBy(() -> Lotto.of(string))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("개수");
    }

    @DisplayName("보내준 볼을 로또에서 가지고 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "19,false"})
    void hasBall(int ballNo, boolean expected) {
        Lotto lotto = Lotto.of("1, 2, 3, 4, 5, 6");
        assertThat(lotto.contains(Ball.of(ballNo))).isEqualTo(expected);
    }

    @DisplayName("보내준 로또와 얼마나 일치하는지 확인")
    @Test
    void hasLottoBall() {
        Lotto lotto = Lotto.of("1, 2, 3, 4, 5, 6");
        Lotto lotto2 = Lotto.of("1, 12, 7, 4, 5, 6");
        assertThat(lotto.countCommonBalls(lotto2)).isEqualTo(4);
    }

    @DisplayName("로또 가져오기 확인")
    @Test
    void getLotto() {
        Lotto lotto = Lotto.of("1, 2, 3, 4, 5, 6");
        List<String> lottoData = lotto.getLotto();
        assertThat(lottoData).containsExactly("1", "2", "3", "4", "5", "6");
    }
}
