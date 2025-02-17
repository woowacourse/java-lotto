package lotto.domain;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProfitTest {
    LottoGeneratorStrategy lottoGenerator = new LottoGeneratorStrategy() {
        @Override
        public List<Integer> generateRandomNumbers() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    };


    @Test
    void 로또_당첨_개수와_수익률이_의도한_대로_리턴되면_통과() {
        // given
        Money purchaseAmount = new Money("2000");
        List<LottoNumber> winningNumber = List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        WinnerLotto winnerLotto = new WinnerLotto(
                new Lotto(new LottoNumbers(winningNumber)).getLottoNumbers().getLottoNumbers(), new LottoNumber(7));
        LottoGroup lottoGroup = new LottoGroup();
        lottoGroup.processLottoTicketGeneration(purchaseAmount, lottoGenerator);

        // when
        Profit result = Profit.calculateProfit(winnerLotto, lottoGroup);
        Map<Rank, Integer> rankCounts = result.getRankCounts();
        final String profitRate = result.calculateAverageProfitRate(purchaseAmount);
        String expectedProfitRate = "2000000.00";

        // then
        Assertions.assertThat(rankCounts.get(Rank.FIRST)).isEqualTo(2);
        Assertions.assertThat(rankCounts.get(Rank.SECOND)).isZero();
        Assertions.assertThat(rankCounts.get(Rank.THIRD)).isZero();
        Assertions.assertThat(rankCounts.get(Rank.FOURTH)).isZero();
        Assertions.assertThat(rankCounts.get(Rank.FIFTH)).isZero();
        Assertions.assertThat(profitRate).isEqualTo(expectedProfitRate);
    }
}
