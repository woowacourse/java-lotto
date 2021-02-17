package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("등수별로 당첨된 개수 세기")
    void match_1stLotto_1stRank() {

        // given
        WinningLotto winningLotto
            = WinningLotto.of(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        List<Lotto> lottoGroup = new ArrayList<>();
        lottoGroup.add(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));

        Lottos lottos = new Lottos(lottoGroup);

        Map<Rank, Long> rankMap = Rank.match(lottos, winningLotto);

        // when
        Long count = rankMap.get(Rank.FIRST);

        // then
        assertThat(count).isEqualTo(1);
    }
}
