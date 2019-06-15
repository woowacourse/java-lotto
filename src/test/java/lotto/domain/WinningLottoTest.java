package lotto.domain;

import lotto.domain.factory.LottoTicketFactory;
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
    void 전달받은번호와_당첨번호의_일치하는_갯수_리턴_테스트() {
        LottoTicket lottoTicket = LottoTicketFactory.getInstance().create("1,3,4,6,7,8");
        WinningLotto winningLotto = WinningLotto.of("1,2,9,10,11,12", 7);

        assertThat(winningLotto.getMatchingCount(lottoTicket) == 1).isTrue();
    }

    @Test
    void 보너스볼과_일치하는_번호가_있을경우_테스트() {
        WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,6", 7);
        LottoTicket lottoTicket = LottoTicketFactory.getInstance().create("1,3,4,6,7,8");

        assertThat(winningLotto.matchesBonusBall(lottoTicket)).isTrue();
    }

    @Test
    void 보너스볼과_일치하는_번호가_없을경우_테스트() {
        WinningLotto winningLotto = WinningLotto.of("1,2,3,4,5,6", 7);
        LottoTicket lottoTicket = LottoTicketFactory.getInstance().create("1,3,4,5,6,8");

        assertThat(winningLotto.matchesBonusBall(lottoTicket)).isFalse();
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

    @Test
    void 보너스볼과_로또번호가_중복됐을때_예외() {
        String winningNumbers = "1,2,3,4,5,7";
        int bonusBall = 7;

        assertThrows(DuplicatedInputException.class, () -> WinningLotto.of(winningNumbers, bonusBall));
    }
}
