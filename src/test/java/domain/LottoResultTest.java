package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void 당첨_결과에_따른_총_상금을_계산한다() {
        final String numbers = "1, 2, 3, 4, 5, 6";
        final String bonusNumber = "7";
        final List<Lotto> lotto = new ArrayList<>();
        lotto.add(new Lotto("1, 2, 3, 4, 5, 6"));
        lotto.add(new Lotto("6, 7, 8, 9, 10, 11"));
        lotto.add(new Lotto("4, 5, 6, 7, 8, 9"));

        Lottos lottos = new Lottos(lotto);
        WinningLotto winningLotto = new WinningLotto(numbers, bonusNumber);
        LottoResult lottoResult = new LottoResult(lottos, winningLotto);

        assertThat(lottoResult.calculateTotalPrice())
                .isEqualTo(2_000_005_000);
    }

    @Test
    void 당첨_결과를_내림차순으로_정렬한다() {
        final String numbers = "1, 2, 3, 4, 5, 6";
        final String bonusNumber = "7";
        final List<Lotto> lotto = new ArrayList<>();
        lotto.add(new Lotto("1, 2, 3, 4, 5, 6"));
        lotto.add(new Lotto("6, 7, 8, 9, 10, 11"));
        lotto.add(new Lotto("4, 5, 6, 7, 8, 9"));

        final Map<RankType, Integer> rankResult = new LinkedHashMap<>();
        rankResult.put(RankType.FIFTH, 1);
        rankResult.put(RankType.FOURTH, 0);
        rankResult.put(RankType.THIRD, 0);
        rankResult.put(RankType.SECOND, 0);
        rankResult.put(RankType.FIRST, 1);
        rankResult.put(RankType.NONE, 1);

        WinningLotto winningLotto = new WinningLotto(numbers, bonusNumber);

        assertThat(LottoResult.sort(winningLotto.evaluateRank(lotto)))
                .isEqualTo(rankResult);
    }
}
