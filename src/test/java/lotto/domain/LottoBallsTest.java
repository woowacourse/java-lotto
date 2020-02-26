package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoBallsTest {
    @Test
    @DisplayName("6개의 숫자가 아닌 다른 개수의 숫자가 들어왔을 경우 테스트")
    void incorrect_six_number() {
        Assertions.assertThatThrownBy(() -> LottoBalls.generateLottoBalls("1,2,3,4"))
                .isInstanceOf(NumberOutOfRangeException.class)
                .hasMessage(LottoBalls.NUMBER_OUT_OF_RANGE_ERROR_MESSAGE);
    }
}
