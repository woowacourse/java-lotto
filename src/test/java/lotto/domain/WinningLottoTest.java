package lotto.domain;

import lotto.exception.UnmatchedLottoTicketAmountException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 객체가_하나만_생성되는지_확인() {
        String winningNumbers = "1,2,3,4,5,6";
        int bonusBall = 7;

        assertThat(WinningLotto.of(winningNumbers, bonusBall) == WinningLotto.of(winningNumbers, bonusBall));
    }

    @Test
    void 요청한_번호와_일치하는_당첨번호_boolean_리턴() {
        String winningNumbers = "1,2,3,4,5,6";
        int bonusBall = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusBall);

        assertThat(winningLotto.hasEqualNumber(LottoNumber.getNumber(4))).isTrue();
    }

    @Test
    void 요청한_번호와_일치하지_않는_당첨번호_boolean_리턴() {
        String winningNumbers = "1,2,3,4,5,6";
        int bonusBall = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusBall);

        assertThat(winningLotto.hasEqualNumber(LottoNumber.getNumber(9))).isFalse();
    }

    @Test
    void 문자가_입력됐을때_예외() {
        String winningNumbers = "1,2,3,4,5,a";
        int bonusBall = 7;

        assertThrows(ArithmeticException.class, () -> WinningLotto.of(winningNumbers, bonusBall));
    }

    @Test
    void 중복된_번호가_입력됐을때_예외() {
        String winningNumbers = "1,2,3,4,5,5";
        int bonusBall = 7;

        assertThrows(UnmatchedLottoTicketAmountException.class, () -> WinningLotto.of(winningNumbers, bonusBall));
    }

    @AfterEach
    void tearDown() {
        WinningLotto.makeObjectNull();
    }
}
