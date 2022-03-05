package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    @DisplayName("보너스볼 중복")
    void duplicated_bonus_ball() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(1);

        assertThatThrownBy(() -> {
            new WinningLotto(lotto, bonusBall);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또의 숫자가 모두 일치할 때 1등 반환")
    void return_rank_first() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusBall);

        assertThat(winningLotto.getLottoRank(lotto)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("로또의 숫자가 5개 일치하고 보너스볼이 일치할 때 2등 반환")
    void return_rank_second() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusBall);

        assertThat(winningLotto.getLottoRank(lotto)).isEqualTo(Rank.SECOND);
    }
}
