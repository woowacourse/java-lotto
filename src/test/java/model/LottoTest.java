package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {
    Lotto lotto;

    @BeforeEach
    void setUp() {
        //[3, 5, 16, 36, 39, 44]
        lotto = new Lotto(0);
    }

    @DisplayName("로또 번호 생성 테스트")
    @Test
    void 로또_번호_생성_테스트() {
        List<Integer> lotto = new Lotto(System.currentTimeMillis()).getLotto();
        assertThat(lotto.size()).isEqualTo(6);
        for (int number : lotto) {
            assertThat(number).isBetween(1, 45);
        }
    }

    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되면 2등이다")
    @Test
    void secondRankTest() {
        WinningLotto winningLotto = new WinningLotto("3,5,16,36,39,40");
        winningLotto.setBonus("44");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.SECOND);
    }

    @DisplayName("당첨 번호가 로또 번호와 5개 매치되고, 보너스 번호가 매치되지 않으면 3등이다")
    @Test
    void thirdRankTest() {
        WinningLotto winningLotto = new WinningLotto("3,5,16,36,39,40");
        winningLotto.setBonus("1");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.THIRD);
    }

    @DisplayName("당첨 번호가 로또 번호와 3개 미만으로 매치되면 낙첨이다")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "1,2,4,6,7,8"})
    void failRankTest(String numbers) {
        WinningLotto winningLotto = new WinningLotto(numbers);
        winningLotto.setBonus("45");
        assertThat(lotto.getRank(winningLotto)).isEqualTo(Rank.FAIL);
    }
}