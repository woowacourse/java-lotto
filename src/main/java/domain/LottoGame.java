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
        this.lottoTickets = LottoTickets.of(selfTicketNumbers, new Money(purchaseMoney), numberGenerateStrategy);
    }

    public void inputWinningNumbers(Set<Integer> winningNumbers, int bonusNumber) {
        this.winningTicket = WinningTicket.of(winningNumbers, bonusNumber);
    }

    public double calculateLottoRateOfReturn() {
        checkCanGetResult();
        double totalReturn = calculateTotalReturn();
        double purchaseMoney = (double) lottoTickets.getTickets().size() * LottoTicket.TICKET_PRICE;
        return totalReturn / purchaseMoney;
    }

    public WinningResult createWinningResult() {
        checkCanGetResult();
        return WinningResult.of(lottoTickets, winningTicket, winningPrizeStrategy);
    }

    private void checkCanGetResult() {
        if (this.lottoTickets == null || this.winningTicket == null) {
            throw new IllegalStateException(NOT_INPUT_RESULT_ERROR_MESSAGE);
        }
    }

    private int calculateTotalReturn() {
        return createWinningResult().sumTotalReturn();
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }
}
