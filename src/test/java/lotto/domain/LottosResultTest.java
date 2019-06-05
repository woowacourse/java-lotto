package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottosResultTest {
    @Test
    void 반환한_로또_결과_확인() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6");

        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "2, 3, 4, 5, 6, 7",
                                            "3, 4, 5, 6, 7, 8", "4, 5, 6, 7, 8, 9",
                                            "5, 6, 7, 8, 9, 10", "6, 7, 8, 9, 10, 11");
        ManualLottoCreator creator = new ManualLottoCreator(inputs);
        Lottos lottos = new Lottos(6, creator);

        LottosResult result = new LottosResult(winningLotto, lottos);

        double expectedROI = (double) 2001555000 / 6000;
        assertThat(result.getROI()).isEqualTo(expectedROI);
    }
}
