package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTest {

    @DisplayName("로또 여섯개 볼 안받았을 경우 예외 테스트")
    @Test
    void makeLottoCountNotSix() {
        Set<Ball> balls = new HashSet<>(Arrays
            .asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5)));
        assertThatThrownBy(() -> new Lotto(balls)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 볼의 개수가 적절하지 않습니다.");
    }

    @DisplayName("보내준 볼을 로또에서 가지고 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "19,false"})
    void hasBall(int ballNo, boolean expected) {
        Set<Ball> balls = new HashSet<>(Arrays
            .asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6)));
        Lotto lotto = new Lotto(balls);
        assertThat(lotto.contains(Ball.of(ballNo))).isEqualTo(expected);
    }

    @DisplayName("보내준 로또와 얼마나 일치하는지 확인")
    @Test
    void hasLottoBall() {
        Set<Ball> balls = new HashSet<>(Arrays
            .asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6)));
        Lotto lotto = new Lotto(balls);
        Set<Ball> balls2 = new HashSet<>(Arrays
            .asList(Ball.of(1), Ball.of(12), Ball.of(7), Ball.of(4), Ball.of(5), Ball.of(6)));
        Lotto lotto2 = new Lotto(balls2);
        assertThat(lotto.countCommonBalls(lotto2)).isEqualTo(4);
    }

    @DisplayName("로또 가져오기 확인")
    @Test
    void getLotto() {
        Set<Ball> balls = new HashSet<>(Arrays
            .asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6)));
        Lotto lotto = new Lotto(balls);
        List<String> lottoData = lotto.getLotto();
        assertThat(lottoData).containsExactly("1", "2", "3", "4", "5", "6");
    }

    @DisplayName("수동 로또 생성 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6", "43, 23, 36, 12, 10, 05"})
    void makeLotto(int one, int two, int three, int four, int five, int six) {
        Lotto lotto = Lotto
            .of(one + ", " + two + ", " + three + ", " + four + ", " + five + ", " + six);
        assertThat(lotto.contains(Ball.of(one))).isTrue();
        assertThat(lotto.contains(Ball.of(two))).isTrue();
        assertThat(lotto.contains(Ball.of(three))).isTrue();
        assertThat(lotto.contains(Ball.of(four))).isTrue();
        assertThat(lotto.contains(Ball.of(five))).isTrue();
        assertThat(lotto.contains(Ball.of(six))).isTrue();
    }

    @DisplayName("수동 로또 생성 실패 - 개수 초과")
    @ParameterizedTest
    @CsvSource(value = {"1, 2, 3, 4, 5, 6, 7", "1, 2, 3, 4, 5"})
    void differentCount(String string) {
        assertThatThrownBy(() -> Lotto.of(string))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("개수");
    }
}
