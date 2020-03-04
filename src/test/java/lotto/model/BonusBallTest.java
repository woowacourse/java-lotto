package lotto.model;

import lotto.exception.OverlapWinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {
    private WinNumber winNumber = new WinNumber(new LottoTicket(
            asList(LottoNumber.lottoNumber(1),
                    LottoNumber.lottoNumber(3),
                    LottoNumber.lottoNumber(4),
                    LottoNumber.lottoNumber(44),
                    LottoNumber.lottoNumber(34),
                    LottoNumber.lottoNumber(13))));

    @Test
    void checkNullWinNumber() {
        assertThatThrownBy(() -> {
            new BonusBall(null, LottoNumber.lottoNumber(1));
        }).isInstanceOf(NullPointerException.class)
        .hasMessage("당첨 번호의 값이 null 입니다.");
    }

    @Test
    @DisplayName("입력한 보너스 볼 값이 당첨 번호와 중복될 경우")
    void isContainsWinNumber() {
        assertThatThrownBy(() -> {
            new BonusBall(winNumber, LottoNumber.lottoNumber(1));
        }).isInstanceOf(OverlapWinNumberException.class)
        .hasMessage("당첨번호와 중복되는 숫자가 있습니다.");
    }
}
