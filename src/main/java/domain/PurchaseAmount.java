package domain;

public class PurchaseAmount {
    private final int money;

    private PurchaseAmount(int money) {
        this.money = money;
    }

    public static PurchaseAmount of(int money) {
        return new PurchaseAmount(money);
    }

    public int calculateAvailableQuantity(int price) {
        return money / price;
    }
}
