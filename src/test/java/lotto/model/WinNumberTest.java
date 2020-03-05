package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WinNumberTest {
    private LottoNumber one = LottoNumber.valueOf(1);
    private LottoNumber two = LottoNumber.valueOf(2);
    private LottoNumber three = LottoNumber.valueOf(3);
    private LottoNumber four = LottoNumber.valueOf(4);
    private LottoNumber five = LottoNumber.valueOf(5);
    private LottoNumber six = LottoNumber.valueOf(6);
    private LottoNumber seven = LottoNumber.valueOf(7);

    private WinNumber winNumber = new WinNumber(new LottoTicket(Arrays.asList(one, two, three, four, five, six)));

    @Test
    @DisplayName("당첨 번호에 숫자가 있으면 true 반환")
    void contains() {
        assertTrue(winNumber.contains(one));
        assertTrue(winNumber.contains(two));
        assertTrue(winNumber.contains(three));
        assertTrue(winNumber.contains(four));
        assertTrue(winNumber.contains(five));
        assertTrue(winNumber.contains(six));

        assertFalse(winNumber.contains(seven));
    }
}
