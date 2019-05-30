package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static lotto.domain.Rank.*;

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
        winningLotto = new WinningLotto(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.get(10));
    }

    @Test
    void 번호_일치_결과() {
        LottoResult lottoResult = new LottoResult(winningLotto, lottos);
        assertThat(1).isEqualTo(lottoResult.getCountOfRank(FIRST));
    }

    @Test
    void 번호_일치_결과2() {
        LottoResult lottoResult = new LottoResult(winningLotto, lottos);
        assertThat(2).isEqualTo(lottoResult.getCountOfRank(FOURTH));
    }

    @Test
    void 총_수익률_계산() {
        LottoResult lottoResult = new LottoResult(winningLotto, lottos);
        assertThat(40032100).isEqualTo(lottoResult.getEarningsRate());
    }
}
