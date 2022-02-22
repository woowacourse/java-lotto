import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    @DisplayName("모든 로또의 합 구하기 테스트")
    void sumTotalLottoRankPrizeAmount() {
        LottoMachine lottoMachine = new LottoMachine(new LottoRankJudge(List.of(1,2,3,4,5,6), 7));
        lottoMachine.inputLottoNumbers(List.of(1,2,3,4,5,6));
        LottoResult lottoResult = lottoMachine.getResult();
        assertThat(lottoResult.getTotalPrizeAmount()).isEqualTo(LottoRank.FIRST.getPrizeAmount());
        assertThat(lottoResult.getCountByRank(LottoRank.FIRST)).isEqualTo(1);
    }
}
