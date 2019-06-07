package domain;

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
        Set<LottoNumber> lottoNumbers = new TreeSet<>(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2), new LottoNumber(3), new LottoNumber(7),
                new LottoNumber(8), new LottoNumber(9)));
        Set<LottoNumber> winningNumbers = new TreeSet<>(Arrays.asList(new LottoNumber(1),
                new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)));
        LottoNumber bonusNumber = new LottoNumber(7);

        List<IssuedLotto> issuedLottos = Arrays.asList(new IssuedLotto(lottoNumbers));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        /* When: */
        Statistics statistics = LottoGame.startLottery(issuedLottos, winningLotto);

        /* Then: */
        assertThat(statistics.countsOf(Rank.FIFTH)).isEqualTo(1);
    }
}
