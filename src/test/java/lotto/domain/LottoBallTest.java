package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBallTest {
    @Test
    @DisplayName("로또볼이 올바른 범위(1~45)에 있는지 확인 테스트")
    void check_lotto_ball_range_test() {
        int lottoBall = 45;
        assertThatCode(() -> new LottoBall(lottoBall)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또볼이 올바른 범위(1~45)에 없는 경우 테스트")
    void check_lotto_ball_out_of_range_test() {
        int lottoBall = -1;
        assertThatThrownBy(() -> new LottoBall(lottoBall)).isInstanceOf(NumberOutOfRangeException.class);
    }
}
