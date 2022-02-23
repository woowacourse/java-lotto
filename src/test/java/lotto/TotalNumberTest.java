package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
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


    @Test
    @DisplayName("당첨 번호와 보너스 번호를 가진 set을 반환한다")
    void makeWinningAndBonusNumbers() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");
        LottoNumber bonusNumber = new LottoNumber("7");

        TotalNumber totalNumber = new TotalNumber(winningNumber, bonusNumber);
        Set<LottoNumber> result = totalNumber.getWinningAndBonusNumber();

        assertThat(result).containsExactly(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7)
        );
    }
}
