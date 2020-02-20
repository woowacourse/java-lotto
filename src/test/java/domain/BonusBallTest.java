package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BonusBallTest {
    List<Integer> winningNumber = new ArrayList<>();

    private static Stream<String> bonusBallSetUp() {
        return Stream.of("", " ", null, "askjfakl", "60", "0");
    }

    @DisplayName("BonusBall 생성자 테스트")
    @ParameterizedTest
    @MethodSource("bonusBallSetUp")
    public void bonusBallConstructorTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new BonusBall(this.winningNumber, input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
