package lotto.domain;

import lotto.exception.DuplicatedInputException;
import org.junit.jupiter.api.AfterEach;
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
    void 전달받은번호와_당첨번호를_비교하기_테스트() {
        String winningNumbers = "1,2,3,4,5,6";
        int bonusBall = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusBall);

        assertThat(winningLotto.hasEqualNumber(LottoNumber.getInstance(4))).isTrue();
        assertThat(winningLotto.hasEqualNumber(LottoNumber.getInstance(9))).isFalse();
    }

    @Test
    void 전달받은번호와_보너스볼을_비교하기_테스트() {
        String winningNumbers = "1,2,3,4,5,6";
        int bonusBall = 7;
        WinningLotto winningLotto = WinningLotto.of(winningNumbers, bonusBall);

        assertThat(winningLotto.hasEqualBonusBall(LottoNumber.getInstance(7))).isTrue();
        assertThat(winningLotto.hasEqualBonusBall(LottoNumber.getInstance(6))).isFalse();
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

        assertThrows(DuplicatedInputException.class, () -> WinningLotto.of(winningNumbers, bonusBall));
    }

    @AfterEach
    void tearDown() {
        WinningLotto.makeObjectNull();
    }
}
