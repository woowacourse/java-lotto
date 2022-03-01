package lotto.domain.ball;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BallStorageTest {

    @DisplayName("Ball 객체는 캐싱되어야 한다.")
    @ParameterizedTest(name = "[{index}] 번호 : {0}")
    @ValueSource(ints = {1, 2, 44, 45})
    void ballsCachingTest(final int ballNumber) {
        final Ball actualBall = BallStorage.getBall(ballNumber);
        final Ball expectedBall = BallStorage.getBall(ballNumber);
        assertThat(actualBall).isSameAs(expectedBall);
    }

    @DisplayName("번호가 동일해도, 캐싱되지 않은 Ball 객체와 일치해선 안된다.")
    @ParameterizedTest(name = "[{index}] 번호 : {0}")
    @ValueSource(ints = {1, 2, 44, 45})
    void ballsCaching2Test(final int ballNumber) {
        final Ball actualBall = BallStorage.getBall(ballNumber);
        final Ball expectedBall = new Ball(ballNumber);
        assertThat(actualBall).isNotSameAs(expectedBall);
    }

}
