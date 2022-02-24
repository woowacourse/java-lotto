package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.ball.BallNumberExceptionStatus;

class BallTest {

    @DisplayName("로또 번호는 1~45 범위의 숫자여야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @ValueSource(ints = {-2, -1, 0, 46, 47, 48})
    void rangeOutExceptionTest(final int number) {
        assertThatThrownBy(() -> new Ball(number))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(BallNumberExceptionStatus.BALL_IS_NOT_IN_RANGE.getMessage());
    }

    @DisplayName("생성자 기능 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호 : {0}")
    @ValueSource(ints = {1, 2, 3, 43, 44, 45})
    void initTest(final int number) {
        final Ball ball = new Ball(number);
        assertThat(ball.getBallNumber()).isEqualTo(number);
    }

    @DisplayName("숫자 비교 기능 성공 테스트")
    @ParameterizedTest(name = "[{index}] 번호1 : {0}, 번호2 : {1}")
    @CsvSource(value = {"1,1", "2,2", "33,33", "45,45"}, delimiter = ',')
    void equalsSuccessTest(final int number1, final int number2) {
        final Ball ball1 = new Ball(number1);
        final Ball ball2 = new Ball(number2);
        assertThat(ball1.equals(ball2)).isTrue();
    }

    @DisplayName("숫자 비교 기능 실패 테스트")
    @ParameterizedTest(name = "[{index}] 번호1 : {0}, 번호2 : {1}")
    @CsvSource(value = {"1,2", "2,1", "33,45", "45,33"}, delimiter = ',')
    void equalsFalseTest(final int number1, final int number2) {
        final Ball ball1 = new Ball(number1);
        final Ball ball2 = new Ball(number2);
        assertThat(ball1.equals(ball2)).isFalse();
    }

}
