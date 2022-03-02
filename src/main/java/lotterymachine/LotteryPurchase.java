package lotterymachine;

public class LotteryPurchase {
    private final Money money;
    private final int autoCount;
    private final int passivityCount;


    public LotteryPurchase(int amount, int passivityCount) {
        this.money = new Money(amount);
        this.passivityCount = passivityCount;
        autoCount = getAutoCount(money, passivityCount);
    }

    private int getAutoCount(Money money, int passivityCount) {
        return getTotalCount(money) - passivityCount;
    }

    private int getTotalCount(Money money) {
        return money.getAmount() / Money.PER_LOTTERY_TICKET_PRICE;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getPassivityCount() {
        return passivityCount;
    }

    public int getTotalAmount() {
        return this.money.getAmount();
    }
}