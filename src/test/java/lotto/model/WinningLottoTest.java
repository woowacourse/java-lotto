package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {
    private LottoRule rule = new KoreaLottoRule();
    private Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), rule);
    private WinningLotto winLotto = new WinningLotto(lotto, 12);
    private Lotto userLotto;

    @Test
    void match() {
        userLotto = new Lotto(Arrays.asList(1, 3, 5, 7, 9, 11), rule);
        Rank rank = winLotto.match(userLotto);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }
}