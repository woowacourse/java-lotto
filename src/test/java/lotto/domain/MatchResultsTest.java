package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class MatchResultsTest {

    @Test
    @DisplayName("번호 매칭 결과에 따라 등수별 당첨 개수를 확인한다.")
    void getWinningResult() {
        List<MatchResultDto> matchResultDtos = List.of(
                new MatchResultDto(6, false),
                new MatchResultDto(5, true),
                new MatchResultDto(5, false),
                new MatchResultDto(5, false),
                new MatchResultDto(3, false),
                new MatchResultDto(2, false)
        );
        MatchResults matchResults = new MatchResults(matchResultDtos);

        Map<LottoRank, Integer> winningResult = matchResults.getWinningResult();

        Assertions.assertThat(winningResult.get(LottoRank.FIRST)).isEqualTo(1);
        Assertions.assertThat(winningResult.get(LottoRank.SECOND)).isEqualTo(1);
        Assertions.assertThat(winningResult.get(LottoRank.THIRD)).isEqualTo(2);
        Assertions.assertThat(winningResult.get(LottoRank.FIFTH)).isEqualTo(1);
        Assertions.assertThat(winningResult.get(LottoRank.NONE)).isEqualTo(1);
    }
}
