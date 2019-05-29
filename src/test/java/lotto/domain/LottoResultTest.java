package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    Lottos lottos;
    WinningLotto winningLotto;

    @BeforeEach
    void lottoSet() {
        lottos = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 8, 7)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 8, 7)),
                new Lotto(Arrays.asList(1, 2, 3, 9, 8, 7))
        ));
        winningLotto = new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 번호_일치_결과() {
        LottoResult lottoResult = new LottoResult(winningLotto, lottos);
        List<Rank> ranks = Arrays.asList(Rank.FIRST, Rank.THIRD, Rank.FOURTH, Rank.FOURTH, Rank.FIFTH);
        assertThat(ranks).isEqualTo(lottoResult.getRank());
    }
}
