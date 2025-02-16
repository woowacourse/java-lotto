package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LottoRankTest {

    @Test
    @DisplayName("로또 1등 당첨 테스트")
    void findFirstRank() {
        MatchResultDto matchResultDto = new MatchResultDto(6, false);

        LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResultDto);

        assertThat(lottoRank.getWinningAmount()).isEqualTo(2_000_000_000L);
    }

    @Test
    @DisplayName("로또 2등 당첨 테스트")
    void findSecondRank() {
        MatchResultDto matchResultDto = new MatchResultDto(5, true);

        LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResultDto);

        assertThat(lottoRank.getWinningAmount()).isEqualTo(30_000_000L);
    }

    @Test
    @DisplayName("로또 3등 당첨 테스트")
    void findThirdRank() {
        MatchResultDto matchResultDto = new MatchResultDto(5, false);

        LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResultDto);

        assertThat(lottoRank.getWinningAmount()).isEqualTo(1_500_000L);
    }

    @Test
    @DisplayName("로또 4등 당첨 테스트")
    void findForthRank() {
        MatchResultDto matchResultDto = new MatchResultDto(4, false);

        LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResultDto);

        assertThat(lottoRank.getWinningAmount()).isEqualTo(50_000L);
    }

    @Test
    @DisplayName("로또 5등 당첨 테스트")
    void findFifthRank() {
        MatchResultDto matchResultDto = new MatchResultDto(3, false);

        LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResultDto);

        assertThat(lottoRank.getWinningAmount()).isEqualTo(5_000L);
    }

    @Test
    @DisplayName("로또 미당첨 테스트")
    void findNoneRank() {
        MatchResultDto matchResultDto = new MatchResultDto(0, false);

        LottoRank lottoRank = LottoRank.findRankWithMatchResult(matchResultDto);

        assertThat(lottoRank.getWinningAmount()).isEqualTo(0L);
    }

    @Test
    @DisplayName("등수 정보를 가져올 때, 미당첨이 제외되었는지 확인한다.")
    void getRankInfo() {
        Map<LottoRank, String> rankInfo = LottoRank.getRankInfo();

        assertFalse(rankInfo.containsKey(LottoRank.NO_REWARD));
    }

    @Test
    @DisplayName("각 등수에 대한 메시지가 예상대로 생성되는지 확인한다.")
    void getRankMessage() {
        Map<LottoRank, String> rankInfo = LottoRank.getRankInfo();

        assertEquals("6개 일치 (2000000000원)", rankInfo.get(LottoRank.FIRST_PLACE));
        assertEquals("5개 일치, 보너스 볼 일치(30000000원)", rankInfo.get(LottoRank.SECOND_PLACE));
    }
}
