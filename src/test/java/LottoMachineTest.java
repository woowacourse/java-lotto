import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    @DisplayName("1등 로또 한개의 결과 구하기")
    void sumTotalLottoRankPrizeAmount() {
        LottoMachine lottoMachine = new LottoMachine(new LottoRankJudge(List.of(1,2,3,4,5,6), 7));

        LottoResult lottoResult = lottoMachine.getResult(List.of(List.of(1,2,3,4,5,6)));

        assertThat(lottoResult.getTotalPrizeAmount()).isEqualTo(BigInteger.valueOf(LottoRank.FIRST.getPrizeAmount()));
        assertThat(lottoResult.getCountByRank(LottoRank.FIRST)).isEqualTo(1);
    }
}
