package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;

class BallTest {

    @DisplayName("번호는 1~45 범위의 숫자여야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @ValueSource(ints = {-2, -1, 0, 46, 47, 48})
    void rangeOutExceptionTest(final int number) {
        assertThatThrownBy(() -> Balls.getBall(number))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(BallNumberExceptionStatus.BALL_CANNOT_BE_OUT_OF_RANGE.getMessage());
    }

    @DisplayName("번호 생성 기능 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @ValueSource(ints = {1, 2, 3, 43, 44, 45})
    void initTest(final int number) {
        final Ball ball = Balls.getBall(number);
        assertThat(ball.getBallNumber()).isEqualTo(number);
    }

}
