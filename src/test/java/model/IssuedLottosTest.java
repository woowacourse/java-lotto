package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.generator.ManualGenerator;
import org.junit.jupiter.api.Test;

public class IssuedLottosTest {

    @Test
    void mergeTest() {
        Lotto lotto1 = Lotto.of(List.of(1,2,3,4,5,6));
        Lotto lotto2 = Lotto.of(List.of(7,8,9,10,11,12));
        IssuedLottos from = new IssuedLottos(new ManualGenerator(List.of(lotto1)));
        IssuedLottos to = new IssuedLottos(new ManualGenerator(List.of(lotto2)));
        assertThat(from.getLottos()).contains(lotto1);
        assertThat(to.getLottos()).contains(lotto2);
        assertThat(IssuedLottos.merge(from, to).getLottos()).contains(lotto1, lotto2);
    }

    @Test
    void summarizeTest() {
        Lotto lotto1 = Lotto.of(List.of(1,2,3,4,5,6));
        IssuedLottos issuedLottos = new IssuedLottos(new ManualGenerator(List.of(lotto1)));
        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers(lotto1, LottoNumber.of(7));
        LottoResult result = issuedLottos.summarize(winningLottoNumbers);
        Map<LottoRank, Integer> resultMap = result.getResultMap();
        assertThat(resultMap.get(LottoRank.FIRST)).isEqualTo(1);
    }
}
