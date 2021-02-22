package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ChanceTest {

    @Test
    @DisplayName("구매할 로또 수가 음수일 경우 예외처리")
    void NegativeChance() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            Chance.valueOf("-1")
        ).withMessage("불가능한 로또 개수 입니다.");
    }

    @ParameterizedTest
    @DisplayName("구매할 로또 수가 숫자가 아니거나 Integer 범위 밖인 경우 경우 예외 처리")
    @ValueSource(strings = {"", "abc", "2147483648"})
    void newChanceWithString(String input) {
        assertThatIllegalArgumentException().isThrownBy(() ->
            Chance.valueOf(input)
        ).withMessage("입력이 숫자가 아니거나 Integer 범위를 벗어났습니다.");
    }
}
