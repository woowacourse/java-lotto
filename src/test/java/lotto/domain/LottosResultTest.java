package lotto.domain;

import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosResultTest {
    @Test
    void 반환한_로또_결과_확인() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "12");

        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 12",
                                            "2, 3, 4, 5, 6, 7", "3, 4, 5, 6, 7, 8",
                                            "4, 5, 6, 7, 8, 9", "5, 6, 7, 8, 9, 10",
                                            "6, 7, 8, 9, 10, 11");

        Lottos lottos = new Lottos(LottoFactory.createManualLottos(7, inputs));
        LottosResult result = new LottosResult(winningLotto, lottos);

        double expectedROI = (double) 2031555000 / 7000;
        assertThat(result.getROI()).isEqualTo(expectedROI);
    }
}
