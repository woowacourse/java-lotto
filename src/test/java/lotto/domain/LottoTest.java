package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTest {

    private static final Lotto winningLotto = new Lotto("1,2,3,4,5,6");

    @Test
    @DisplayName("보너스 볼 포함하는 경우")
    void bonusBallDuplicated() {
        Lotto lotto = new Lotto("11,12,13,14,15,16");
        assertEquals(lotto.isContainBonusBall(new BonusBall(11, winningLotto)), true);
    }
}
