package lotto.controller;

import lotto.model.AutoNumbers;
import lotto.model.BonusBall;
import lotto.model.WinNumber;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ControllerTest {
    private AutoNumbers autonumbers = new AutoNumbers(Arrays.asList(1,2,5,7,9,11));

    @Test
    void isInWinNumber() {
        WinNumber winNumber = new WinNumber("3, 4, 5, 6, 7, 8");
        assertThat(Controller.isInWinNumber(autonumbers)).isEqualTo(2);
    }
}
