package domain;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class LottoStatsTest {
    @DisplayName("1등이 3개인 경우")
    @Test
    void _1등이_3개인_경우() {
        List<Lotto> lottos = new ArrayList<>();
        LottoStats lottoStats = new LottoStats(List.of(1,2,3,4,5,6), 7);
        for(int i = 0; i < 3; i ++) {
            lottos.add(new Lotto(List.of(1,2,3,4,5,6)));
        }

        lottoStats.calculateResult(lottos);

        Assertions.assertThat(lottoStats.getRankCount(Rank.FIRST)).isEqualTo(3);
    }

    @DisplayName("총 상금 계산 테스트")
    @Test
    void 총_상금_계산_테스트() {
        List<Lotto> lottos = new ArrayList<>();
        LottoStats lottoStats = new LottoStats(List.of(1,2,3,4,5,6), 7);

        lottos.add(new Lotto(List.of(1,2,3,4,5,6))); // 1
        lottos.add(new Lotto(List.of(1,2,3,4,5,7))); // 2
        lottos.add(new Lotto(List.of(1,2,3,4,5,8))); // 3
        lottos.add(new Lotto(List.of(1,2,3,4,7,8))); // 4
        lottos.add(new Lotto(List.of(1,2,3,7,8,9))); // 5

        lottoStats.calculateResult(lottos);

        Assertions.assertThat(lottoStats.getTotalPrize()).isEqualTo(2031555000L);
    }

}