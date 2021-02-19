package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RanksTest {

    @Test
    @DisplayName("등수 개수를 찾아낸다.")
    public void testFindNumberOfRank() {
        Ranks lottoResult = new Ranks(Arrays.asList(Rank.FIFTH, Rank.FIFTH, Rank.FIFTH, Rank.SECOND));
        assertThat(lottoResult.getNumberOfRank(Rank.FIRST)).isEqualTo(0);
        assertThat(lottoResult.getNumberOfRank(Rank.SECOND)).isEqualTo(1);
        assertThat(lottoResult.getNumberOfRank(Rank.THIRD)).isEqualTo(0);
        assertThat(lottoResult.getNumberOfRank(Rank.FOURTH)).isEqualTo(0);
        assertThat(lottoResult.getNumberOfRank(Rank.FIFTH)).isEqualTo(3);
    }

    @Test
    @DisplayName("수익률을 계산한다")
    public void testCalculateProfitRate() {
        Ranks lottoResult = new Ranks(Arrays.asList(Rank.FIFTH, Rank.NO_MATCH, Rank.NO_MATCH,
                Rank.NO_MATCH, Rank.NO_MATCH, Rank.NO_MATCH, Rank.NO_MATCH, Rank.NO_MATCH,
                Rank.NO_MATCH, Rank.NO_MATCH, Rank.NO_MATCH, Rank.NO_MATCH, Rank.NO_MATCH, Rank.NO_MATCH));
        double profitRate = lottoResult.calculateProfitRate();
        assertThat(profitRate).isEqualTo(5000 / 14000f);
    }

}
