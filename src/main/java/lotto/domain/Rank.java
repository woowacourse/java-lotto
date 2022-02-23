package lotto.domain;

public enum Rank {
    RANK_1(6, false, 2000000000),
    RANK_2(5, true, 30000000),
    RANK_3(5, false, 1500000),
    RANK_4(4, false, 50000),
    RANK_5(3, false, 5000);

    private final int winningNumbersMatchCount;
    private final boolean bonusNumberMatch;
    private final long prize;
    private int count;

    Rank(int winningNumbersMatchCount, boolean bonusNumberMatch, long prize) {
        this.winningNumbersMatchCount = winningNumbersMatchCount;
        this.bonusNumberMatch = bonusNumberMatch;
        this.prize = prize;
        this.count = 0;
    }

    public static void parseRank(Tickets tickets, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        for(Ticket ticket : tickets.getTickets()) {
            int winningNumberMatchCount = ticket.getWinningNumbersMatchCount(winningNumbers);
            boolean bonusNumberMatch = ticket.isBonusNumberMatch(bonusNumber);
        }
    }
}
