package model;

public interface WinningPrizeStrategy {
    WinningPrize winningPrize(int matchCount, boolean matchBonus);

    int matchCount(WinningPrize winningPrize);

    boolean matchBonus(WinningPrize winningPrize);
}
