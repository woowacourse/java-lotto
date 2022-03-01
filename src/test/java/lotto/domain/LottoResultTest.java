package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    Lottos lottos;
    WinningLotto winningLotto;

    @BeforeEach
    void init() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(2, 3, 4, 5, 6, 7));
        Lotto lotto3 = new Lotto(List.of(3, 4, 5, 6, 7, 8));

        lottos = Lottos.newInstanceByLottosMoney(Arrays.asList(lotto1, lotto2, lotto3), new Money(0));
        winningLotto = new WinningLotto(lotto1, new LottoNumber(7));
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 1등")
    void match_lottos_first() {
        LottoResult lottoResult = new LottoResult();

        lottoResult.addMatchingCount(lottos, winningLotto);

        assertEquals(1, lottoResult.getLottoResult().get(Rank.FIRST));
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 2등")
    void match_lottos_second() {
        LottoResult lottoResult = new LottoResult();

        lottoResult.addMatchingCount(lottos, winningLotto);

        assertEquals(1, lottoResult.getLottoResult().get(Rank.SECOND));
    }

    @Test
    @DisplayName("로또들의 일치 개수를 확인 - 3등")
    void match_lottos_third() {
        LottoResult lottoResult = new LottoResult();

        lottoResult.addMatchingCount(lottos, winningLotto);

        assertEquals(1, lottoResult.getLottoResult().get(Rank.FOURTH));
    }
}
