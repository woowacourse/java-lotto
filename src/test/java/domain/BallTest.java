package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.lotto.Ball;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BallTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또_번호가_범위가_아니면__예외를_던진다(int number) {
        assertThatThrownBy(() -> new Ball(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
