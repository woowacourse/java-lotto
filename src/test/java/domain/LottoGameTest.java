package domain;

import domain.lottonumber.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {
    @Test
    void 로또_추첨_결과에_따른_통계를_제대로_만들어내는지_테스트() {
        /* Given : */
        Set<LottoNumber> lottoNumbers = new TreeSet<>(Arrays.asList(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(7), LottoNumber.valueOf(8),
                LottoNumber.valueOf(9)));
        Set<LottoNumber> winningNumbers = new TreeSet<>(Arrays.asList(LottoNumber.valueOf(1),
                LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5),
                LottoNumber.valueOf(6)));

        IssuedLottos issuedLottos = IssuedLottos.of(Arrays.asList(new Lotto(lottoNumbers)));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNumber);

        /* When: */
        Statistics statistics = LottoGame.startLottery(issuedLottos, winningLotto);

        /* Then: */
        assertThat(statistics.countsOf(Rank.FIFTH)).isEqualTo(1);
    }
}
