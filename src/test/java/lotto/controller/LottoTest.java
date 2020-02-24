package lotto.controller;

import lotto.model.LottoTicket;
import lotto.model.WinNumber;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {
    private LottoTicket autonumbers = new LottoTicket(Arrays.asList(1, 2, 5, 7, 9, 11));
    private WinNumber winNumber = new WinNumber(Arrays.asList(3, 4, 5, 6, 7, 8));

//    @Test
//    @DisplayName("당첨 번호 안에 있는 값이 몇 개인지")
//    void isInWinNumber() {
//        assertThat(Lotto.isInWinNumber(autonumbers)).isEqualTo(2);
//    }
}
