package model;

public class WinningResultDto {
    private final int matchCount;
    private final boolean matchBonus;
    private final int prizeMoney;
    private final int ticketCount;

    public WinningResultDto(int matchCount, boolean matchBonus, int prizeMoney, int ticketCount) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prizeMoney = prizeMoney;
        this.ticketCount = ticketCount;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isMatchBonus() {
        return matchBonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getTicketCount() {
        return ticketCount;
    }
}
