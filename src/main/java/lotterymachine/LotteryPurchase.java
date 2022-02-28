package lotterymachine;

public class LotteryPurchase {
    private static final int PER_LOTTERY_TICKET_PRICE = 1000;

    private int number;

    public LotteryPurchase(int number) {
        validateNumber(number);
        this.number = purchase(number);
    }

    private int purchase(int number) {
        return this.number = number / PER_LOTTERY_TICKET_PRICE;
    }

    public int getCount() {
        return number;
    }

    public int getPurchasePrice() {
        return this.number * PER_LOTTERY_TICKET_PRICE;
    }

    private void validateNumber(int number) {
        if (number < PER_LOTTERY_TICKET_PRICE) {
            throw new IllegalArgumentException("로또 구매는 기본 1000원 이상부터 할 수 있습니다.");
        }
    }
}
