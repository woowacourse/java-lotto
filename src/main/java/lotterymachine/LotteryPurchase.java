package lotterymachine;

public class LotteryPurchase {
    private final Money money;
    private final int autoCount;
    private final int passivityCount;


    public LotteryPurchase(int amount, int passivityCount) {
        this.money = new Money(amount);
        validateCount(passivityCount);
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

    private void validateCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("구매 개수는 음수가 들어올 수 없습니다.");
        }
    }
}