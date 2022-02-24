package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    void 투입금액이_정상() {
        assertThatCode(() -> new Amount(10000))
            .doesNotThrowAnyException();
    }

    @Test
    void 투입금액_0() {
        assertThatThrownBy(() -> new Amount(0))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 투입금액_음수() {
        assertThatThrownBy(() -> new Amount(-1000))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 투입금액_천원단위로_안나누어떨어질때() {
        assertThatThrownBy(() -> new Amount(100))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
