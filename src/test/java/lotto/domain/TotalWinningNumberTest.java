package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TotalWinningNumberTest {

    @Test
    @DisplayName("보너스 넘버가 당첨번호와 중복될 경우 예외를 발생시킨다")
    void throwExceptionWhenDuplicate() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");
        LottoNumber bonusNumber = new LottoNumber("6");

        assertThatThrownBy(() -> new TotalWinningNumber(winningNumber, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("당첨 번호와 보너스 번호를 가진 set을 반환한다")
    void makeWinningAndBonusNumbers() {
        WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");
        LottoNumber bonusNumber = new LottoNumber("7");

        TotalWinningNumber totalWinningNumber = new TotalWinningNumber(winningNumber, bonusNumber);
        Set<LottoNumber> result = totalWinningNumber.getWinningAndBonusNumber();
        Set<LottoNumber> expected = initExpected();

        assertThat(result).isEqualTo(expected);
    }

    private HashSet<LottoNumber> initExpected() {
        return new HashSet<>(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(7)
        ));
    }
}
