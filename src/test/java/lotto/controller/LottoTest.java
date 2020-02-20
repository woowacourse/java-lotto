package lotto.controller;

import lotto.model.AutoTicket;
import lotto.model.WinNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    private AutoTicket autonumbers = new AutoTicket(Arrays.asList(1, 2, 5, 7, 9, 11));
    private WinNumber winNumber = new WinNumber("3, 4, 5, 6, 7, 8");

    @Test
    @DisplayName("당첨 번호 안에 있는 값이 몇 개인지")
    void isInWinNumber() {
        assertThat(Lotto.isInWinNumber(autonumbers)).isEqualTo(2);
    }
}
