package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BallTest {

    @DisplayName("볼은 1~45 범위의 숫자여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 0, 46, 47, 48})
    void rangeOutExceptionTest(final int number) {
        assertThatThrownBy(() -> new Ball(number))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(BallNumberExceptionStatus.BALL_IS_NOT_IN_RANGE.getMessage());
    }

    @DisplayName("생성자 기능 테스트")
    @ParameterizedTest
    @ValueSource(ints = {43, 44, 45})
    void initTest(final int number) {
        assertDoesNotThrow(() -> new Ball(number));
    }

    @DisplayName("숫자 비교 기능 테스트")
    @Test
    void equalsNumberTest() {
        final Ball ball = new Ball(40);
        final Ball ball2 = new Ball(40);
        assertThat(ball.equals(ball2)).isTrue();
    }
}
