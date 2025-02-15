package lotto.domain;

import lotto.constant.WinningTier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreboardTest {

    @DisplayName("발행된 로또의 당첨 등수를 찾을 수 있다.")
    @Test
    void 발행된_로또의_당첨_등수를_찾을_수_있다() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                new Lotto(List.of(8, 9, 10, 11, 12, 13)));
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Scoreboard scoreboard = new Scoreboard();

        List<WinningTier> actualTiers = scoreboard.findWinningTiers(lottos, winningLotto);

        assertThat(actualTiers).containsExactlyInAnyOrderElementsOf(Arrays.stream(WinningTier.values()).toList());
    }
}