package model;

public enum WinLottoInfo {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(2, 0);

    private final int matchNumberCount;
    private final int price;

    WinLottoInfo(int matchNumberCount, int price) {
        this.matchNumberCount = matchNumberCount;

        this.price = price;
    }


    public int getMatchNumberCount() {
        return matchNumberCount;
    }

    public int getPrice() {
        return price;
    }
}
