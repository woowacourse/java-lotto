package lotto.domain;

import lotto.utils.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameResultTest {

    @DisplayName("랭크 별 개수 테스트")
    @Test
    void testAdd() {
        LottoGameResult lottoGameResult = new LottoGameResult();
        List<Rank> matchRank = Arrays.asList(
                Rank.rankOf(4, false),
                Rank.rankOf(3, false),
                Rank.rankOf(2, false),
                Rank.rankOf(1, false),
                Rank.rankOf(0, false)
        );
        lottoGameResult.add(matchRank);

        assertThat(lottoGameResult.countByRank(Rank.FOURTH)).isEqualTo(1);
        assertThat(lottoGameResult.countByRank(Rank.NOTHING)).isEqualTo(3);
    }


    @DisplayName("수익률 계산 테스트")
    @Test
    void testCalculateProfit() {
        LottoGenerator fixedGenerator = new FixedGenerator();
        Lottos fixedLottos = new Lottos(Arrays.asList(fixedGenerator.generate()));

        Lotto fixedWinningLotto = new Lotto(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));

        WinningLotto winningLotto = new WinningLotto(fixedWinningLotto, 7);
        LottoGameResult fixedGameResult = new LottoGameResult();
        fixedGameResult.add(fixedLottos.findMatchLotto(winningLotto));

        double profit = fixedGameResult.calculateProfit();

        assertThat(profit).isEqualTo(2_000_000);
    }
}
