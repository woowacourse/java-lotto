package lotto.domain;

import lotto.exception.BonusBallDuplicatedException;
import lotto.exception.BonusBallScopeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BonusBallTest {
    private static final Lotto lotto = Lotto.from("1,2,3,4,5,6");

    @Test
    @DisplayName("보너스 볼 중복될 경우")
    void bonusBallDuplicated() {
        assertThatThrownBy(() -> {
            new BonusBall(1, lotto);
        }).isInstanceOf(BonusBallDuplicatedException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1 ~ 45 이외의 수일 경우")
    void bonusBallOutOfScope(int input) {
        assertThatThrownBy(() -> {
            new BonusBall(input, lotto);
        }).isInstanceOf(BonusBallScopeException.class);
    }
}