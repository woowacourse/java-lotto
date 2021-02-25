package lottogame.domain.lotto;

import lottogame.domain.lotto.*;
import lottogame.domain.stats.LottoResults;
import lottogame.utils.ManualLottoGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


// 나중에 지울 것
class LottoResultTest {
    private ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator("1, 2, 3, 4, 5, 6");
    private WinningLotto winningLotto = new WinningLotto(manualLottoGenerator.generateLotto(), LottoNumber.of("7"));

    @Test
    void 로또_결과_테스트() {
        manualLottoGenerator = new ManualLottoGenerator();
        manualLottoGenerator.addResources(Arrays.asList(
                "8, 21, 23, 41, 42, 43",
                "3, 5, 11, 16, 32, 38",
                "7, 11, 16, 35, 36, 44",
                "1, 8, 11, 31, 41, 42",
                "13, 14, 16, 38, 42, 45",
                "7, 11, 30, 40, 42, 43",
                "2, 13, 22, 32, 38, 45",
                "23, 25, 33, 36, 39, 41",
                "1, 3, 5, 14, 22, 45",
                "5, 9, 38, 41, 43, 44",
                "2, 8, 9, 18, 19, 21",
                "13, 14, 18, 21, 23, 35",
                "17, 21, 29, 37, 42, 45",
                "3, 8, 27, 30, 35, 44"));
        List<Lotto> manualLottos = manualLottoGenerator.generateLottos();
        Lottos lottos = new Lottos(manualLottos);
        LottoGame lottoGame = new LottoGame(lottos, winningLotto);
        LottoResults lottoResults = lottoGame.results();
//        
    }
}
