package lotto.domain;

import lotto.Exception.NumberOutOfRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoBallTest {
    @Test
    void 로또볼_값_올바른범위안에_있는지_검증() {
        int lottoBall = 45;
        Assertions.assertThatCode(()->new LottoBall(lottoBall)).doesNotThrowAnyException();
    }
    @Test
    void 로또볼_값_올바른범위안에_없을경우_검증() {
        int lottoBall = -1;
        Assertions.assertThatThrownBy(()->new LottoBall(lottoBall)).isInstanceOf(NumberOutOfRangeException.class);
    }
}
