package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusBallTest {
    @DisplayName("BonusBall 생성자 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void bonusBallConstructorTest(int input) {
        Assertions.assertThatThrownBy(() -> {
            new BonusBall(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
