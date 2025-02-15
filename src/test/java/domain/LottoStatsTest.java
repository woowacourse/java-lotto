package domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoStatsTest {
    @DisplayName("1등이 3개인 경우")
    @Test
    void _1등이_3개인_경우() {
        List<Lotto> lottos = new ArrayList<>();
        LottoStats lottoStats = new LottoStats(List.of(1, 2, 3, 4, 5, 6), 7);
        for (int i = 0; i < 3; i++) {
            lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        }

        lottoStats.calculateResult(lottos);

        assertThat(lottoStats.getRankCount(Rank.FIRST)).isEqualTo(3);
    }

    @DisplayName("기본 테스트 케이스")
    @Test
    void 기본_테스트_케이스() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(23, 25, 33, 36, 39, 41)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45)),
                new Lotto(List.of(5, 9, 38, 41, 43, 44)),
                new Lotto(List.of(2, 8, 9, 18, 19, 21)),
                new Lotto(List.of(13, 14, 18, 21, 23, 35)),
                new Lotto(List.of(17, 21, 29, 37, 42, 45)),
                new Lotto(List.of(3, 8, 27, 30, 35, 44)));

        LottoStats lottoStats = new LottoStats(List.of(1, 2, 3, 4, 5, 6), 7);
        lottoStats.calculateResult(lottos);

        assertThat(lottoStats.getRankCount(Rank.FIRST)).isEqualTo(0);
        assertThat(lottoStats.getRankCount(Rank.SECOND)).isEqualTo(0);
        assertThat(lottoStats.getRankCount(Rank.THIRD)).isEqualTo(0);
        assertThat(lottoStats.getRankCount(Rank.FOURTH)).isEqualTo(0);
        assertThat(lottoStats.getRankCount(Rank.FIFTH)).isEqualTo(1);
        assertThat(lottoStats.getEarningRate(14000)).isEqualTo("0.35");
    }

    @DisplayName("총 상금 계산 테스트")
    @Test
    void 총_상금_계산_테스트() {
        List<Lotto> lottos = new ArrayList<>();
        LottoStats lottoStats = new LottoStats(List.of(1, 2, 3, 4, 5, 6), 7);

        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6))); // 1
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 7))); // 2
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 8))); // 3
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 7, 8))); // 4
        lottos.add(new Lotto(List.of(1, 2, 3, 7, 8, 9))); // 5

        lottoStats.calculateResult(lottos);

        assertThat(lottoStats.getTotalPrize()).isEqualTo(2031555000L);
    }

}