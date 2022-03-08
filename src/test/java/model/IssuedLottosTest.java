package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import model.generator.LottosGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IssuedLottosTest {

    LottosGenerator lottosGenerator;
    Lotto lotto1;
    Lotto lotto2;

    @BeforeEach
    void setUp() {
        lotto1 = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        lotto2 = Lotto.of(List.of(7, 8, 9, 10, 11, 12));
        lottosGenerator = () -> List.of(lotto1, lotto2);
    }

    @Test
    void summarizeTest() {
        Lotto lotto1 = Lotto.of(List.of(1, 2, 3, 4, 5, 6));
        IssuedLottos issuedLottos = IssuedLottos.generatedBy(lottosGenerator);
        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers(lotto1, LottoNumber.of(7));
        LottoResult result = issuedLottos.summarize(winningLottoNumbers);
        Map<LottoRank, Integer> resultMap = result.getResultMap();
        assertThat(resultMap.get(LottoRank.FIRST)).isEqualTo(1);
    }
}
