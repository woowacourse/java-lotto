package lotto.domain;

public enum Rank {
    RANK_1(6, 0, 2000000000),
    RANK_2(5, 1, 30000000),
    RANK_3(5, 0, 1500000),
    RANK_4(4, 0, 50000),
    RANK_5(3, 0, 5000);

    private final int winningNumbersMatchCount;
    private final int bonusNumberMatchCount;
    private final long prize;
    private int count;

    Rank(int winningNumbersMatchCount, int bonusNumberMatchCount, long prize) {
        this.winningNumbersMatchCount = winningNumbersMatchCount;
        this.bonusNumberMatchCount = bonusNumberMatchCount;
        this.prize = prize;
        this.count = 0;
    }

    public static void parseRank(Tickets tickets, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        for(Ticket ticket : tickets.getTickets()) {
            int winningNumberMatchCount = ticket.getWinningNumbersMatchCount(winningNumbers);
        }
    }
}
