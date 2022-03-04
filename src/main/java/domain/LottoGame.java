package domain;

import domain.strategy.NumberGenerateStrategy;
import domain.strategy.WinningPrizeStrategy;
import java.util.List;
import java.util.Set;

public class LottoGame {
    public static final String NOT_INPUT_RESULT_ERROR_MESSAGE = "로또 티켓을 구매하고, 당첨 번호를 입력해야 결과를 얻을 수 있습니다.";
    private final WinningPrizeStrategy winningPrizeStrategy;
    private LottoTickets lottoTickets;
    private WinningTicket winningTicket;

    public LottoGame(WinningPrizeStrategy winningPrizeStrategy) {
        this.winningPrizeStrategy = winningPrizeStrategy;
    }

    public void purchaseLottoTickets(List<Set<Integer>> selfTicketNumbers, int purchaseMoney,
                                     NumberGenerateStrategy numberGenerateStrategy) {
        LottoTickets selfPurchaseTickets = LottoTickets.from(selfTicketNumbers);
        LottoTickets autoPurchaseTickets = LottoTickets.of(new LottoMoney(purchaseMoney), numberGenerateStrategy);
        this.lottoTickets = selfPurchaseTickets.concat(autoPurchaseTickets);
    }

    public void inputWinningNumbers(Set<Integer> winningNumbers, int bonusNumber) {
        this.winningTicket = WinningTicket.of(winningNumbers, bonusNumber);
    }

    public LottoResult createWinningResult() {
        checkCanGetResult();
        return LottoResult.of(lottoTickets, winningTicket, winningPrizeStrategy);
    }

    private void checkCanGetResult() {
        if (this.lottoTickets == null || this.winningTicket == null) {
            throw new IllegalStateException(NOT_INPUT_RESULT_ERROR_MESSAGE);
        }
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }
}
