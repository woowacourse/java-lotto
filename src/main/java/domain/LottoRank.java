package domain;

public enum LottoRank {

    RANK_1(2000000000),
    RANK_2(30000000),
    RANK_3(1500000),
    RANK_4(50000),
    RANK_5(5000);

    private final int price;


    LottoRank(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
