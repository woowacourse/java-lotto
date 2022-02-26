package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.LottoNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호에 보너스 볼이 있으면 예외 발생")
    public void checkBonusBallInWinningNumbersTest() {
        LottoNumber bonusBall = LottoNumber.generateLottoNumber(4);
        Lotto lotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(
                () -> new WinningLotto(lotto, bonusBall)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6})
    @DisplayName("로또가 보너스 볼을 가질 때 테스트")
    void isBonusBallMatchTest(int number) {
        LottoNumber bonusBall = LottoNumber.generateLottoNumber(number);
        Lotto lotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        Lotto WinningNumber = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 8, 9));
        WinningLotto winningLotto = new WinningLotto(WinningNumber, bonusBall);

        assertThat(winningLotto.isBonusBallMatch(lotto)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8})
    @DisplayName("로또가 보너스 볼을 안가질 때 테스트")
    void isNotBonusBallMatchTest(int number) {
        LottoNumber bonusBall = LottoNumber.generateLottoNumber(number);
        Lotto lotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        Lotto WinningNumber = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(WinningNumber, bonusBall);

        assertThat(winningLotto.isBonusBallMatch(lotto)).isFalse();
    }
}