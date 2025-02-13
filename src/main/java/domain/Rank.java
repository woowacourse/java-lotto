package domain;

public enum Rank {
    NONE("", 0L, 0, false),
    FIFTH("3개 일치 (5000원)- ", 5000L, 3, false),

    FOURTH("4개 일치 (50000원)- ", 50000L, 4, false),

    THIRD("5개 일치 (1500000원)- ", 1500000L, 5, false),

    SECOND("5개 일치, 보너스 볼 일치(30000000원)- ", 30000000L, 5, true),

    FIRST("6개 일치 (2000000000원)- ", 2000000000L, 6, false);


    private final String message;
    private Long prize;
    private int count;
    private boolean bonusMatch;

    Rank(String message, Long prize, int count, boolean bonusMatch) {
        this.message = message;
        this.prize = prize;
        this.count = count;
        this.bonusMatch = bonusMatch;
    }

    public static Rank fromResult(int matchCount, boolean contains) {
        Rank[] ranks = Rank.values();
        for(int i = ranks.length - 1; i >= 0; i --){
            if(ranks[i].getCount() == matchCount && (!ranks[i].isBonusMatch() || contains)) {
                return ranks[i];
            }
        }
        return NONE;
    }

    public String getMessage() {
        return message;
    }

    public Long getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }
}
