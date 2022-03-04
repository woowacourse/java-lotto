package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.DefaultWinningPrizeStrategy;
import domain.strategy.WinningPrizeStrategy;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGameTest {

    private final WinningPrizeStrategy winningPrizeStrategy = new DefaultWinningPrizeStrategy();
    private final NumberGenerateStrategy numberGenerateStrategy = () -> Set.of(1, 2, 3, 4, 5, 6);
    private final LottoGame lottoGame = new LottoGame(numberGenerateStrategy, winningPrizeStrategy);

    @Test
    @DisplayName("로또 게임이 입력받은 금액으로 자동구매를 잘 하는지 확인한다.")
    void checkMoneyTicketCount() {
        LottoMoney purchaseMoney = new LottoMoney(17000);
        LottoTickets lottoTickets = lottoGame.purchaseAutoTickets(purchaseMoney);
        assertThat(lottoTickets.getTickets().size()).isEqualTo(purchaseMoney.getValue() / LottoGame.TICKET_PRICE);
    }

    @Test
    @DisplayName("로또 게임이 생성 전략으로 똑바로 티켓을 발행하는지 확인한다.")
    void checkGenerateTicket() {
        LottoMoney purchaseMoney = new LottoMoney(1000);
        LottoTickets lottoTickets = lottoGame.purchaseAutoTickets(purchaseMoney);
        assertThat(lottoTickets.getTickets().get(0).getLottoNumberValues())
                .isEqualTo(LottoTicket.fromNumberValues(Set.of(1, 2, 3, 4, 5, 6)).getLottoNumberValues());
    }

    @Test
    @DisplayName("로또 게임이 상금으로 맞춘 갯수를 잘 찾는지 확인한다.")
    void checkMatchCount() {
        assertThat(lottoGame.findMatchCount(WinningPrize.SECOND)).isEqualTo(5);
    }

    @Test
    @DisplayName("로또 게임이 상금으로 보너스 숫자를 맞췄는지 판단한다.")
    void checkMatchBonus() {
        assertThat(lottoGame.findMatchBonus(WinningPrize.SECOND)).isTrue();
    }
}