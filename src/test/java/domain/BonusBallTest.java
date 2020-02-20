package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BonusBallTest {
    @DisplayName("BonusBall 생성자 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "askjfakl", "60", "0", "2"})
    public void bonusBallConstructorTest(String input) {
        List<Integer> winningNumber = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6));
        Assertions.assertThatThrownBy(() -> {
            new BonusBall(winningNumber, input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
