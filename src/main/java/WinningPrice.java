public enum WinningPrice {

    All(2_000_000_000),
    FiveAndBonus(30_000_000),
    Five(1_500_000),
    Four(50_000),
    Three(5_000);

    private final int price;

    WinningPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
