package domain;

public class LottoGame {
    private final LottoTickets lottoTickets;
    private final WinningTicket winningTicket;
    private final WinningPrizeStrategy winningPrizeStrategy;

    public LottoGame(LottoTickets lottoTickets,
                     WinningTicket winningTicket,
                     WinningPrizeStrategy winningPrizeStrategy) {
        this.lottoTickets = lottoTickets;
        this.winningTicket = winningTicket;
        this.winningPrizeStrategy = winningPrizeStrategy;
    }

    public WinningResult getWinningResult() {
        return WinningResult.from(lottoTickets, winningTicket, winningPrizeStrategy);
    }

    public double getLottoRateOfReturn() {
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
