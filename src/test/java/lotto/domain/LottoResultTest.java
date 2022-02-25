package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoResultTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void before() {
        Lotto winLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Ball bonusBall = new Ball(7);
        winningLotto = new WinningLotto(winLotto, bonusBall);
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 1등")
    void match_lottos_first() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(lotto));
        LottoResult lottoResult = new LottoResult();

        lottoResult.match(lottos, winningLotto);

        assertEquals(lottoResult.getLottoResult().get(Rank.FIRST), 1);
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 2등")
    void match_lottos_second() {
        Lotto lotto = new Lotto(Arrays.asList(2, 3, 4, 5, 6, 7));
        Lottos lottos = new Lottos(List.of(lotto));
        LottoResult lottoResult = new LottoResult();

        lottoResult.match(lottos, winningLotto);

        assertEquals(lottoResult.getLottoResult().get(Rank.SECOND), 1);
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 4등")
    void match_lottos_fourth() {
        Lotto lotto = new Lotto(Arrays.asList(3, 4, 5, 6, 7, 8));
        Lottos lottos = new Lottos(List.of(lotto));
        LottoResult lottoResult = new LottoResult();

        lottoResult.match(lottos, winningLotto);

        assertEquals(lottoResult.getLottoResult().get(Rank.FOURTH), 1);
    }
}
