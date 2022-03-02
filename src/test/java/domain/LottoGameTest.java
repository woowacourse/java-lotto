package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.DefaultWinningPrizeStrategy;
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
    private final WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();
    private final NumberGenerateStrategy numberGenerateStrategy = () -> new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
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
        lottoGame.purchaseLottoTickets(new ArrayList<>(), purchaseMoney, numberGenerateStrategy);
        lottoGame.inputWinningNumbers(winningNumbers, bonusNumber);
        assertThat(lottoGame.calculateLottoRateOfReturn())
                .isEqualTo(WinningPrize.FIRST.getPrizeMoney() / (double) purchaseMoney);
    }

    @Test
    @DisplayName("로또 게임에 금액 입력시 로또 티켓이 갯수에 맞게 구매되는 지 확인한다.")
    void checkPurchaseLottoTickets() {
        int purchaseMoney = 1000;
        lottoGame = new LottoGame(winningPrizeStrategy);
        lottoGame.purchaseLottoTickets(new ArrayList<>(), purchaseMoney, numberGenerateStrategy);
        LottoTickets lottoTickets = lottoGame.getLottoTickets();
        assertThat(lottoTickets.getTickets().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 티켓이 입력되지 않았을 시 결과를 요청하면 에러를 던지는지 확인한다.")
    void checkNoPurchaseTicketError() {
        lottoGame = new LottoGame(winningPrizeStrategy);
        assertThatThrownBy(() -> lottoGame.createWinningResult())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(LottoGame.NOT_INPUT_RESULT_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("당첨 티켓이 입력되지 않았을 시 결과를 요청하면 에러를 던지는지 확인한다.")
    void checkNoInputWinningNumberError() {
        lottoGame = new LottoGame(winningPrizeStrategy);
        int purchaseMoney = 1000;
        lottoGame.purchaseLottoTickets(new ArrayList<>(), purchaseMoney, numberGenerateStrategy);
        assertThatThrownBy(() -> lottoGame.createWinningResult())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(LottoGame.NOT_INPUT_RESULT_ERROR_MESSAGE);
    }
}