package lotto.model;

import lotto.exception.NotNumberException;
import lotto.exception.NotSixNumbersException;
import lotto.exception.OverlapWinNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WinLottoNumbersTest {
    @Test
    @DisplayName("숫자가 아닌 값")
    void validateNumberFormat() {
        assertThatThrownBy(() -> {
            new WinLottoNumbers("1, 3, 5, 7, a, 11", "2");
        }).isInstanceOf(NotNumberException.class)
                .hasMessage("숫자를 입력하세요.");
    }

    @Test
    @DisplayName("보너스볼 입력시 당첨번호에 있는 숫자이면 오류 메시지 호출")
    void validateContainsWinNumber() {
        assertThatThrownBy(() -> {
            WinLottoNumbers winLottoNumbers = new WinLottoNumbers("1, 2, 3, 4, 5, 6", "1");
        }).isInstanceOf(OverlapWinNumberException.class)
            .hasMessage("당첨번호와 중복되는 숫자가 있습니다.");
    }
}
