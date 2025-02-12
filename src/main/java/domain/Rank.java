package domain;

public enum Rank {
    NONE("", 0L),
    FIFTH("3개 일치 (5000원)- ", 5000L),

    FOURTH("4개 일치 (50000원)- ", 50000L),

    THIRD("5개 일치 (1500000원)- ", 1500000L),

    SECOND("5개 일치, 보너스 볼 일치(30000000원)- ", 30000000L),

    FIRST("6개 일치 (2000000000원)- ", 2000000000L);


    private final String message;
    private Long prize;

    Rank(String message, Long prize) {
        this.message = message;
        this.prize = prize;
    }

    public String getMessage() {
        return message;
    }

    public Long getPrize() {
        return prize;
    }
}
