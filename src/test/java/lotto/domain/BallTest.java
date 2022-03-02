package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BallTest {

    @Test
    @DisplayName("로또 번호 캐시 생성")
    void cache_balls() {
        List<Ball> balls = Ball.getTotalBalls();

        assertEquals(balls.size(), 45);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1에서 45 사이의 문자열 값이 아닌 값을 입력했을 경우")
    void incorrect_range_1_to_45(int input) {
        assertThatThrownBy(() -> {
            Ball ball = new Ball(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 비교")
    void equals() {
        Ball ball1 = new Ball(1);
        Ball ball2 = new Ball(1);

        assertEquals(ball1, ball2);
    }

    @Test
    @DisplayName("해시코드 비교")
    void hash_code() {
        Ball ball1 = new Ball(1);
        Ball ball2 = new Ball(1);

        assertSame(ball1.hashCode(), ball2.hashCode());
    }
}
