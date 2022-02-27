package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    private WinningLotto winningLotto;

    @BeforeEach
    public void setUp() {
        Lotto lotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = LottoNumber.generateLottoNumber(7);
        winningLotto = new WinningLotto(lotto, bonusBall);
    }

    @Test
    @DisplayName("6개 일치 -> 1등 당첨 테스트")
    public void checkFirstWinTest() {
        Lotto allMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        Rank rank = allMatchLotto.match(winningLotto);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 일치, 보너스 볼 일치 -> 2등 당첨 테스트")
    public void checkBonusBallMatchSecondWinTest() {
        Lotto fiveMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 7));
        Rank rank = fiveMatchLotto.match(winningLotto);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개일치, 보너스 볼 불일치 -> 3등 당첨 테스트")
    void checkThirdWinTest() {
        Lotto fiveMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 44));
        Rank rank = fiveMatchLotto.match(winningLotto);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개 일치 -> 4등 당첨 테스트")
    void checkFourthWinTest() {
        Lotto fourMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 43, 44));
        Rank rank = fourMatchLotto.match(winningLotto);

        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개 일치 -> 5등 당첨 테스트")
    void checkFifthWinTest() {
        Lotto threeMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 42, 43, 44));
        Rank rank = threeMatchLotto.match(winningLotto);

        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("꽝 테스트")
    void checkNoWinTest() {
        Lotto noMatchLotto = new Lotto(LottoNumberGenerator.of(1, 2, 41, 42, 43, 44));
        Rank rank = noMatchLotto.match(winningLotto);

        assertThat(rank).isEqualTo(Rank.SIXTH);
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

    @Test
    @DisplayName("1부터 6사이의 로또 생성시 일치 테스트")
    void generateOneToSixLottoNumberTest() {
        Lotto lotto = Lotto.generateLottoNumbers(1, 6);
        Lotto actual = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto).isEqualTo(actual);
    }

    @Test
    @DisplayName("5부터 10사이의 로또 생성시 일치 테스트")
    void generateFiveToTenLottoNumberTest() {
        Lotto lotto = Lotto.generateLottoNumbers(5, 10);
        Lotto actual = new Lotto(LottoNumberGenerator.of(5, 6, 7, 8, 9, 10));
        assertThat(lotto).isEqualTo(actual);
    }
}
