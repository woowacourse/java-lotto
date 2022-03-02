package domain;

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

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }

    public void purchaseLottoTickets(List<Set<Integer>> selfTicketNumbers, int purchaseMoney,
                                     GenerateStrategy generateStrategy) {
        this.lottoTickets = LottoTickets.from(selfTicketNumbers, new Money(purchaseMoney), generateStrategy);
    }

    public void inputWinningNumbers(Set<Integer> winningNumbers, int bonusNumber) {
        this.winningTicket = WinningTicket.from(winningNumbers, bonusNumber);
    }

    public WinningResult getWinningResult() {
        checkCanGetResult();
        return WinningResult.from(lottoTickets, winningTicket, winningPrizeStrategy);
    }

    private void checkCanGetResult() {
        if (this.lottoTickets == null || this.winningTicket == null) {
            throw new IllegalStateException(NOT_INPUT_RESULT_ERROR_MESSAGE);
        }
    }

    public double getLottoRateOfReturn() {
        checkCanGetResult();
        double totalReturn = calculateTotalReturn();
        double purchaseMoney = (double) lottoTickets.getTickets().size() * LottoTicket.TICKET_PRICE;
        return totalReturn / purchaseMoney;
    }

    private int calculateTotalReturn() {
        return getWinningResult().getCountOfWinning()
                .entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
