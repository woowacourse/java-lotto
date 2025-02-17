package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class RankTest {
    @DisplayName("fromResult 테스트")
    @Test
    void fromResult() {
        assertThat(Rank.fromResult(6, true)).isEqualTo(Rank.FIRST);
        assertThat(Rank.fromResult(6, false)).isEqualTo(Rank.FIRST);

        assertThat(Rank.fromResult(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.fromResult(5, false)).isEqualTo(Rank.THIRD);

        assertThat(Rank.fromResult(4, true)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.fromResult(4, false)).isEqualTo(Rank.FOURTH);

        assertThat(Rank.fromResult(3, true)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.fromResult(3, false)).isEqualTo(Rank.FIFTH);

        assertThat(Rank.fromResult(2, true)).isEqualTo(Rank.NONE);
        assertThat(Rank.fromResult(2, false)).isEqualTo(Rank.NONE);
    }

    @DisplayName("makeLottoResult 테스트")
    @Test
    void makeLottoResultTest(){
        List<Lotto> lottos = IntStream.range(0,3)
                .mapToObj(i -> new Lotto(List.of(1,2,3,4,5,6 + i)))
                .toList();
        List<Integer> winningNumber = List.of(1,2,3,4,5,6);
        int bonusNumber = 7;
        LottoStats lottoStats = Rank.makeLottoResult(lottos,winningNumber,bonusNumber);

        assertThat(lottoStats.getRankCount(Rank.FIRST)).isEqualTo(1);
        assertThat(lottoStats.getRankCount(Rank.SECOND)).isEqualTo(1);
        assertThat(lottoStats.getRankCount(Rank.THIRD)).isEqualTo(1);
        assertThat(lottoStats.getRankCount(Rank.FOURTH)).isEqualTo(0);
        assertThat(lottoStats.getRankCount(Rank.FIFTH)).isEqualTo(0);
        assertThat(lottoStats.getRankCount(Rank.NONE)).isEqualTo(0);
    }
}
