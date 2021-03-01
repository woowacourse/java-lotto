package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoRankTest {
    @DisplayName("findLottoRank NONE_MATCHES 테스트")
    @Test
    void FindLottoRankNoneMatches() {
        assertThat(LottoRank.findLottoRank(0, false)).isEqualTo(LottoRank.NO_PRIZE);
        assertThat(LottoRank.findLottoRank(0, true)).isEqualTo(LottoRank.NO_PRIZE);
        assertThat(LottoRank.findLottoRank(1, false)).isEqualTo(LottoRank.NO_PRIZE);
        assertThat(LottoRank.findLottoRank(1, true)).isEqualTo(LottoRank.NO_PRIZE);
        assertThat(LottoRank.findLottoRank(2, false)).isEqualTo(LottoRank.NO_PRIZE);
        assertThat(LottoRank.findLottoRank(2, true)).isEqualTo(LottoRank.NO_PRIZE);
    }

    @DisplayName("findLottoRank THREE_MATCHES 테스트")
    @Test
    void FindLottoRankThreeMatches() {
        assertThat(LottoRank.findLottoRank(3, false)).isEqualTo(LottoRank.FIFTH_PRIZE);
        assertThat(LottoRank.findLottoRank(3, true)).isEqualTo(LottoRank.FIFTH_PRIZE);
    }

    @DisplayName("findLottoRank FOUR_MATCHES 테스트")
    @Test
    void FindLottoRankFourMatches() {
        assertThat(LottoRank.findLottoRank(4, false)).isEqualTo(LottoRank.FOURTH_PRIZE);
        assertThat(LottoRank.findLottoRank(4, true)).isEqualTo(LottoRank.FOURTH_PRIZE);
    }

    @DisplayName("findLottoRank FIVE_MATCHES, FIVE_AND_BONUS_MATCHES 테스트")
    @Test
    void FindLottoRankFiveMatches() {
        assertThat(LottoRank.findLottoRank(5, false)).isEqualTo(LottoRank.THIRD_PRIZE);
        assertThat(LottoRank.findLottoRank(5, true)).isEqualTo(LottoRank.SECOND_PRIZE);
    }

    @DisplayName("findLottoRank SIX_MATCHES 테스트")
    @Test
    void FindLottoRankSixMatches() {
        assertThat(LottoRank.findLottoRank(6, false)).isEqualTo(LottoRank.FIRST_PRIZE);
    }

    @DisplayName("getPrizeMoney 테스트")
    @Test
    void getPrizeMoneyTest() {
        assertThat(LottoRank.NO_PRIZE.getPrizeMoney()).isEqualTo(0);
    }
}
