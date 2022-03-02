package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.GenerateStrategy;
import domain.strategy.LottoWinningPrizeStrategy;
import domain.strategy.WinningPrizeStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {

    private final Set<Integer> winningNumbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private final WinningPrizeStrategy winningPrizeStrategy = new LottoWinningPrizeStrategy();
    private final GenerateStrategy generateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private LottoGame lottoGame;

    @AfterEach
    void afterEach() {
        lottoGame = null;
    }

    @Test
    @DisplayName("로또 게임이 총 수익률을 잘 계산하는지 확인한다.")
    void checkRateOfReturn() {
        int purchaseMoney = 1000;
        int bonusNumber = 7;
        lottoGame = new LottoGame(winningPrizeStrategy);
        lottoGame.purchaseLottoTickets(new ArrayList<>(), purchaseMoney, generateStrategy);
        lottoGame.inputWinningNumbers(winningNumbers, bonusNumber);
        assertThat(lottoGame.calculateLottoRateOfReturn())
                .isEqualTo(WinningPrize.FIRST.getPrizeMoney() / (double) purchaseMoney);
    }
}