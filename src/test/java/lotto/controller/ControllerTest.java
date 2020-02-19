package lotto.controller;

import lotto.model.AutoNumbers;
import lotto.model.BonusBall;
import lotto.model.WinNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {
    private AutoNumbers autonumbers = new AutoNumbers(Arrays.asList(1, 2, 5, 7, 9, 11));
    private WinNumber winNumber = new WinNumber("3, 4, 5, 6, 7, 8");

    @Test
    @DisplayName("당첨 번호 안에 있는 값이 몇 개인지")
    void isInWinNumber() {
        assertThat(Controller.isInWinNumber(autonumbers)).isEqualTo(2);
    }

    @Test
    @DisplayName("보너스 볼 값을 갖고 있는지")
    void hasBonusNumber() {
        BonusBall bonusBall = new BonusBall("2");
        assertTrue(Controller.hasBonusBall(autonumbers));
    }
}
