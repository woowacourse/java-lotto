package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static lotto.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoResultTest {
    Lotto lotto;
    Lottos lottos;
    WinningLotto winningLotto;
    LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottos = new Lottos(Arrays.asList(lotto));
        winningLotto = new WinningLotto(lotto, LottoNumber.of(7));
        lottoResult = LottoResult.generateLottoResult(lottos, winningLotto);
    }

    @Test
    void 생성확인() {
        assertThat(lottoResult).isEqualTo(new LottoResult(Arrays.asList(FIRST)));
    }

    @Test
    void 총이익률_확인() {
        assertEquals(lottoResult.findYield(1000), 200000000);
    }
}
