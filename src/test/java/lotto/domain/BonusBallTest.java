package lotto.domain;

import lotto.exception.BonusBallException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BonusBallTest {
    private static final Lotto lotto = new Lotto("1,2,3,4,5,6");

    @Test
    @DisplayName("보너스 볼 중복될 경우")
    void bonusBallDuplicated() {
        assertThatThrownBy(() -> {
            new BonusBall(1, lotto);
        }).isInstanceOf(BonusBallException.class);
    }
}