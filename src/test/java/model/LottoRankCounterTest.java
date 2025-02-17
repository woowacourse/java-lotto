package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LottoRankCounterTest {

    private LottoRankCounter lottoRankCounter = new LottoRankCounter();


    @Test
    public void LottRank의_개수를_계산하여_Map의_value로_저장한다() {
        // given
        List<LottoRank> lottoRanks = List.of(
                LottoRank.FIRST, LottoRank.SECOND, LottoRank.FIRST, LottoRank.THIRD, LottoRank.FIRST
        );

        // when
        Map<LottoRank, Integer> result = lottoRankCounter.countLottoRanks(lottoRanks);

        // then
        assertThat(result.get(LottoRank.FIRST)).isEqualTo(3);
        assertThat(result.get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.get(LottoRank.THIRD)).isEqualTo(1);
    }
}
