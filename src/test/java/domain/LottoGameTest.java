package domain;

import domain.lottonumber.LottoNumber;
import domain.lottonumber.LottoNumberPool;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {
    @Test
    void 로또_추첨_결과에_따른_통계를_제대로_만들어내는지_테스트() {
        /* Given : */
        Set<LottoNumber> lottoNumbers = new TreeSet<>(Arrays.asList(LottoNumberPool.pickLottoNumber(1),
                LottoNumberPool.pickLottoNumber(2), LottoNumberPool.pickLottoNumber(3),
                LottoNumberPool.pickLottoNumber(7), LottoNumberPool.pickLottoNumber(8),
                LottoNumberPool.pickLottoNumber(9)));
        Set<LottoNumber> winningNumbers = new TreeSet<>(Arrays.asList(LottoNumberPool.pickLottoNumber(1),
                LottoNumberPool.pickLottoNumber(2), LottoNumberPool.pickLottoNumber(3),
                LottoNumberPool.pickLottoNumber(4), LottoNumberPool.pickLottoNumber(5),
                LottoNumberPool.pickLottoNumber(6)));
        LottoNumber bonusNumber = LottoNumberPool.pickLottoNumber(7);

        IssuedLottos issuedLottos = IssuedLottos.of(Arrays.asList(new IssuedLotto(lottoNumbers)));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        /* When: */
        Statistics statistics = LottoGame.startLottery(issuedLottos, winningLotto);

        /* Then: */
        assertThat(statistics.countsOf(Rank.FIFTH)).isEqualTo(1);
    }
}
