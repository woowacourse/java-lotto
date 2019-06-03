package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static lotto.domain.Rank.*;

public class LottoResultTest {
    Lottos lottos;
    WinningLotto winningLotto;

    @BeforeEach
    void lottoSet() {
        lottos = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(
                        LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(4),
                        LottoNumber.getInstance(5),
                        LottoNumber.getInstance(6)
                )),
                new Lotto(Arrays.asList(
                        LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(4),
                        LottoNumber.getInstance(5),
                        LottoNumber.getInstance(7)
                )),
                new Lotto(Arrays.asList(
                        LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(4),
                        LottoNumber.getInstance(8),
                        LottoNumber.getInstance(7)
                )),
                new Lotto(Arrays.asList
                        (LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(4),
                        LottoNumber.getInstance(8),
                        LottoNumber.getInstance(7)
                )),
                new Lotto(Arrays.asList(
                        LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(9),
                        LottoNumber.getInstance(8),
                        LottoNumber.getInstance(7)
                ))
        ));
        winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(4),
                        LottoNumber.getInstance(5),
                        LottoNumber.getInstance(6)
                )),
                LottoNumber.getInstance(10));
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
        assertThat(BigInteger.valueOf(40032100)).isEqualTo(lottoResult.getEarningsRate());
    }
}
