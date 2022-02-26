package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("로또 상금을 옳바르게 찾는지 테스트한다.")
public class LottoTest {

    @Test
    @DisplayName("3개 일치시 5등이다.")
    void lotto_calculateRightFifthRank() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 10, 11, 12), 45);

        Prize winnerPrice = winningLotto.calculateRank(lotto);
        assertThat(winnerPrice).isEqualTo(Prize.FIFTH);
    }

    @Test
    @DisplayName("5개 일치와 보너스가 있다면 2등이다.")
    void lotto_calculateRightSecondRank() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 12), 6);

        Prize winnerPrice = winningLotto.calculateRank(lotto);
        assertThat(winnerPrice).isEqualTo(Prize.SECOND);
    }

}
