package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {
    Lotto lotto;

    private static class FixedNumberGenerator implements RandomNumberGenerator {
        @Override
        public Set<Integer> generateNumbers() {
            return Set.of(1, 2, 3, 4, 5, 6);
        }
    }

    @BeforeEach
    void setUp() {
        lotto = new Lotto(new FixedNumberGenerator());
    }

    @DisplayName("당첨 번호가 로또 번호와 6개 매치되면 1등이다")
    @Test
    void firstRankTest() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");
        winningLotto.setBonus("45");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.FIRST);
    }

    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되면 2등이다")
    @Test
    void secondRankTest() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,7");
        winningLotto.setBonus("6");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되지 않으면 3등이다")
    @Test
    void thirdRankTest() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,7");
        winningLotto.setBonus("45");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("당첨 번호가 로또 번호와 4개 매치되면 4등이다")
    @Test
    void fourthRankTest() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,7,8");
        winningLotto.setBonus("45");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("당첨 번호가 로또 번호와 3개 매치되면 5등이다")
    @Test
    void fifthRankTest() {
        WinningLotto winningLotto = new WinningLotto("1,2,3,7,8,9");
        winningLotto.setBonus("45");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("당첨 번호가 로또 번호와 3개 미만으로 매치되면 낙첨이다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,7,8,9,10", "1,7,8,9,10,11"})
    void failRankTest(String numbers) {
        WinningLotto winningLotto = new WinningLotto(numbers);
        winningLotto.setBonus("45");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.FAIL);
    }
}