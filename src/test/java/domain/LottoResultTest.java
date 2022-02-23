package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @ParameterizedTest
    @EnumSource(Rank.class)
    void 당첨_개수_확인(Rank rank) {
        List<Rank> lottos = Arrays.asList(Rank.values());
        //when
        LottoResult lottoResult = new LottoResult(lottos);
        Map<Rank, Long> rankCounts = lottoResult.countRank();

        assertThat(rankCounts.get(rank)).isEqualTo(1L);
    }
}
