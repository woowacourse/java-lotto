package lotto.domain;

public enum LottoRank {
    FIRST_PLACE(6,2_000_000_000L, false),
    SECOND_PLACE(5,30_000_000L, true),
    THIRD_PLACE(5,1_500_000L, false),
    FORTH_PLACE(4,50_000L, false),
    FIFTH_PLACE(3,5_000L, false);

    private final int matchCount;
    private final long winningAmount;
    private final boolean isBonus;

    LottoRank(int matchCount, long winningAmount, boolean isBonus) {
        this.matchCount = matchCount;
        this.winningAmount = winningAmount;
        this.isBonus = isBonus;
    }

    public String formatResult(int count) {
        return String.format("%d개 일치 (%d원)- %d개", matchCount, winningAmount, count);
    }
}
