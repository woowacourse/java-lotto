package domain.result;

import domain.result.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoRankTest {
    @DisplayName("findLottoRank NONE_MATCHES 테스트")
    @Test
    void FindLottoRankNoneMatches() {
        assertThat(LottoRank.findLottoRank(0, false)).isEqualTo(LottoRank.NONE_MATCHES);
        assertThat(LottoRank.findLottoRank(0, true)).isEqualTo(LottoRank.NONE_MATCHES);
        assertThat(LottoRank.findLottoRank(1, false)).isEqualTo(LottoRank.NONE_MATCHES);
        assertThat(LottoRank.findLottoRank(1, true)).isEqualTo(LottoRank.NONE_MATCHES);
        assertThat(LottoRank.findLottoRank(2, false)).isEqualTo(LottoRank.NONE_MATCHES);
        assertThat(LottoRank.findLottoRank(2, true)).isEqualTo(LottoRank.NONE_MATCHES);
    }

    @DisplayName("findLottoRank THREE_MATCHES 테스트")
    @Test
    void FindLottoRankThreeMatches() {
        assertThat(LottoRank.findLottoRank(3, false)).isEqualTo(LottoRank.THREE_MATCHES);
        assertThat(LottoRank.findLottoRank(3, true)).isEqualTo(LottoRank.THREE_MATCHES);
    }

    @DisplayName("findLottoRank FOUR_MATCHES 테스트")
    @Test
    void FindLottoRankFourMatches() {
        assertThat(LottoRank.findLottoRank(4, false)).isEqualTo(LottoRank.FOUR_MATCHES);
        assertThat(LottoRank.findLottoRank(4, true)).isEqualTo(LottoRank.FOUR_MATCHES);
    }

    @DisplayName("findLottoRank FIVE_MATCHES, FIVE_AND_BONUS_MATCHES 테스트")
    @Test
    void FindLottoRankFiveMatches() {
        assertThat(LottoRank.findLottoRank(5, false)).isEqualTo(LottoRank.FIVE_MATCHES);
        assertThat(LottoRank.findLottoRank(5, true)).isEqualTo(LottoRank.FIVE_AND_BONUS_MATCHES);
    }

    @DisplayName("findLottoRank SIX_MATCHES 테스트")
    @Test
    void FindLottoRankSixMatches() {
        assertThat(LottoRank.findLottoRank(6, false)).isEqualTo(LottoRank.SIX_MATCHES);
    }

    @DisplayName("getPrizeMoney 테스트")
    @Test
    void getPrizeMoneyTest() {
        assertThat(LottoRank.NONE_MATCHES.getPrizeMoney()).isEqualTo(0);
    }
}
