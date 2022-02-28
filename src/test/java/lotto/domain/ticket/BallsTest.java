package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;

class BallsTest {

    @DisplayName("범위 밖의 번호는 생성할 수 없다.")
    @ParameterizedTest(name = "[{index}] 번호 : {0}")
    @ValueSource(ints = {-1, 0, 46, 47})
    void ballsOutOfRangeExceptionTest(final int ballNumber) {
        assertThatThrownBy(() -> Balls.getBall(ballNumber))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(BallNumberExceptionStatus.BALL_CANNOT_BE_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("범위 안의 번호는 생성할 수 있다.")
    @ParameterizedTest(name = "[{index}] 번호 : {0}")
    @ValueSource(ints = {1, 2, 44, 45})
    void ballsGenerateTest(final int ballNumber) {
        assertDoesNotThrow(() -> Balls.getBall(ballNumber));
    }
    
    @DisplayName("번호는 캐싱되어야 한다.")
    @ParameterizedTest(name = "[{index}] 번호 : {0}")
    @ValueSource(ints = {1, 2, 44, 45})
    void ballsCachingTest(final int ballNumber) {
        final Ball actualBall = Balls.getBall(ballNumber);
        final Ball expectedBall = Balls.getBall(ballNumber);
        assertThat(actualBall).isSameAs(expectedBall);
    }

}
