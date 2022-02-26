package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    @DisplayName("일치하는 번호 개수에 따른 순위 확인 테스트")
    public void checkMatchNumber() {
        Lotto allMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = LottoNumber.generateLottoNumber(7);
        WinningLotto winningNumber = new WinningLotto(allMatchLotto, bonusBall);
        Rank rank = allMatchLotto.match(winningNumber);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("보너스 볼과 일치할 때 2등 당첨 테스트")
    public void checkBonusBallMatchTest() {
        Lotto fiveMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 44));
        Lotto lotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = LottoNumber.generateLottoNumber(44);

        WinningLotto winningNumber = new WinningLotto(lotto, bonusBall);
        Rank rank = fiveMatchLotto.match(winningNumber);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("당첨 번호가 6자리가 아니면 예외 발생")
    public void checkWinningNumberIs6Test() {
        assertThatThrownBy(
                () -> new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외 발생")
    public void checkDuplicatedNumberTest() {

        assertThatThrownBy(
                () -> new Lotto(LottoNumberGenerator.of(1, 1, 3, 4, 5, 46))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
