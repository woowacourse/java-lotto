package model;

public enum Prize {
    _1ST(6, false, 2_000_000_000),
    _2ND(5, true, 30_000_000),
    _3RD(5, false, 1_500_000),
    _4TH(4, false, 50_000),
    _5TH(3, false, 5_000)
    ;

    final int count;
    final boolean bonus;
    final int price;

    Prize(int price, boolean bonus, int count) {
        this.price = price;
        this.bonus = bonus;
        this.count = count;
    }
}
