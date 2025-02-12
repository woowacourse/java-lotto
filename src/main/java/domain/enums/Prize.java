package domain.enums;

public enum Prize {
    EMPTY("", 0, false),
    FIFTH("3개 일치", 5_000, false),
    FOURTH("4개 일치", 50_000, false),
    THIRD("5개 일치", 150_000, false),
    SECOND("5개 일치, 보너스 볼 일치", 30_000_000, true),
    FIRST("6개 일치", 2_000_000_000, false),
    ;

    private String correctMessage;
    private int prizeMoney;
    private boolean isBonusCorrected;

    Prize(String correctMessage, int prizeMoney, boolean isBonusCorrected) {
        this.correctMessage = correctMessage;
        this.prizeMoney = prizeMoney;
        this.isBonusCorrected = isBonusCorrected;
    }

    public int getPrizeMoney() {
        return this.prizeMoney;
    }
}
