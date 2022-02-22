import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankJudgeTest {

    @Test
    @DisplayName("1등 판독 테스트")
    void firstPrize() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        LottoRankJudge lottoRankJudge = new LottoRankJudge(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoRank rank = lottoRankJudge.judge(lottoNumbers);
        assertThat(rank).isEqualTo(LottoRank.FIRST);
    }
}
