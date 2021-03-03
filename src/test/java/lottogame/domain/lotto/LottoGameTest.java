package lottogame.domain.lotto;

import lottogame.domain.stats.LottoResults;
import lottogame.domain.stats.Money;
import lottogame.domain.stats.Rank;
import lottogame.domain.stats.Yield;
import lottogame.utils.ManualLottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoGameTest {
    private ManualLottoGenerator manualLottoGenerator;
    private LottoGame lottoGame;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        manualLottoGenerator = new ManualLottoGenerator();
        manualLottoGenerator.addResources(Arrays.asList(
                "1, 2, 3, 4, 45, 44", "1, 2, 3, 43, 44, 45",
                "1, 2, 3, 4, 5, 7", "1, 2, 3, 4, 5, 44"));
        Lottos lottos = new Lottos(manualLottoGenerator.generateLottos());
        manualLottoGenerator = new ManualLottoGenerator("1, 2, 5, 20, 21, 22");
        winningLotto = new WinningLotto(manualLottoGenerator.generateLotto(), LottoNumber.of(25));
        lottoGame = new LottoGame(lottos);
    }

    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void constructor() {
        manualLottoGenerator = new ManualLottoGenerator();
        manualLottoGenerator.addResources(Arrays.asList(
                "1, 2, 3, 4, 45, 44", "1, 2, 3, 43, 44, 45",
                "1, 2, 3, 4, 5, 7", "1, 2, 3, 4, 5, 44"));
        Lottos newLottos = new Lottos(manualLottoGenerator.generateLottos());
        manualLottoGenerator = new ManualLottoGenerator("1, 2, 5, 20, 21, 22");
        LottoGame newLottoGame = new LottoGame(newLottos);
        assertEquals(newLottoGame, lottoGame);
    }

    @Test
    @DisplayName("올바른 결과를 만드는지 확인")
    void results() {
        EnumMap<Rank, Integer> results = new EnumMap<>(Rank.class);
        results.put(Rank.NOT_FOUND, 0);
        results.put(Rank.FIFTH, 2);
        results.put(Rank.FOURTH, 0);
        results.put(Rank.THIRD, 0);
        results.put(Rank.SECOND, 0);
        results.put(Rank.FIRST, 0);
        LottoResults exectedlottoResults = new LottoResults(results, Yield.of(Money.of(10000), Money.of(4000)));
        assertEquals(exectedlottoResults, lottoGame.results(winningLotto, Money.of(4000)));
    }
}
