package lotto.model;

import lotto.exception.OverlapWinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusBallTest {
    private WinNumber winNumber = new WinNumber(asList(new LottoNumber(1), new LottoNumber(3), new LottoNumber(4), new LottoNumber(44), new LottoNumber(34), new LottoNumber(13)));
    @Test
    @DisplayName("입력한 보너스 볼 값이 당첨 번호와 중복될 경우")
    void isContainsWinNumber() {
        assertThatThrownBy(() -> {
            new BonusBall(winNumber, new LottoNumber(1));
        }).isInstanceOf(OverlapWinNumberException.class)
        .hasMessage("당첨번호와 중복되는 숫자가 있습니다.");
    }
}
