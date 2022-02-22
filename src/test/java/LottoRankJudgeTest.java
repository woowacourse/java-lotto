import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankJudgeTest {

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        LottoRankJudge lottoRankJudge = new LottoRankJudge(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoRank rank = lottoRankJudge.judge(List.of(1, 2, 3, 4, 5, 6));
        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("2등 판독 테스트")
    void secondPrize() {
        LottoRankJudge lottoRankJudge = new LottoRankJudge(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoRank rank = lottoRankJudge.judge(List.of(1, 2, 3, 4, 5, 7));
        assertThat(rank).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("3등 판독 테스트")
    void thirdPrize() {
        LottoRankJudge lottoRankJudge = new LottoRankJudge(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoRank rank = lottoRankJudge.judge(List.of(1, 2, 3, 4, 5, 8));
        assertThat(rank).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("4등 판독 테스트")
    void fourthPrize() {
        LottoRankJudge lottoRankJudge = new LottoRankJudge(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoRank rank = lottoRankJudge.judge(List.of(1, 2, 3, 4, 8, 9));
        assertThat(rank).isEqualTo(LottoRank.FOURTH);
    }

    @Test
    @DisplayName("5등 판독 테스트")
    void fifthPrize() {
        LottoRankJudge lottoRankJudge = new LottoRankJudge(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoRank rank = lottoRankJudge.judge(List.of(1, 2, 3, 8, 9, 10));
        assertThat(rank).isEqualTo(LottoRank.FIFTH);
    }
}
