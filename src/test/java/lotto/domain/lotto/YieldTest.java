package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class YieldTest {

    @Test
    @DisplayName("음수 수익률 생성시 예외처리")
    void negativeYield() {
        assertThatIllegalArgumentException().isThrownBy(
            () -> new Yield(-0.000001)
        ).withMessage("존재할 수 없는 수익률입니다.");
    }

    @Test
    @DisplayName("정상 수익률 생성")
    void newYield() {
        assertThatCode(() -> {
            new Yield(0);
            new Yield(1.6);
        }).doesNotThrowAnyException();
    }
}
