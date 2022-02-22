package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TotalNumberTest {

    @Test
    @DisplayName("보너스 넘버가 당첨번호와 중복될 경우 예외를 발생시킨다")
    void throwExceptionWhenDuplicate() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");
        LottoNumber bonusNumber = new LottoNumber("6");

        assertThatThrownBy(() -> new TotalNumber(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
