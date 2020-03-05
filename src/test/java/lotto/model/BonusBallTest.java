package lotto.model;

import lotto.exception.OverlapWinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {
    private WinNumber winNumber = new WinNumber(new LottoTicket(
            asList(LottoNumber.valueOf(1),
                    LottoNumber.valueOf(3),
                    LottoNumber.valueOf(4),
                    LottoNumber.valueOf(44),
                    LottoNumber.valueOf(34),
                    LottoNumber.valueOf(13))));

    @Test
    void checkNullWinNumber() {
        assertThatThrownBy(() -> {
            new BonusBall(null, LottoNumber.valueOf(1));
        }).isInstanceOf(NullPointerException.class)
                .hasMessage("당첨 번호의 값이 null 입니다.");
    }

    @Test
    @DisplayName("입력한 보너스 볼 값이 당첨 번호와 중복될 경우")
    void isContainsWinNumber() {
        assertThatThrownBy(() -> {
            new BonusBall(winNumber, LottoNumber.valueOf(1));
        }).isInstanceOf(OverlapWinNumberException.class)
                .hasMessage("당첨번호와 중복되는 숫자가 있습니다.");
    }
}
