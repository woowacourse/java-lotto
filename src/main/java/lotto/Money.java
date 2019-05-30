package lotto;

public class Money {
    private final int money;

    public Money(String input) {
        int currentMoney = Integer.parseInt(input);
        if (currentMoney < 1000 || currentMoney % 1000 != 0) {
            throw new IllegalMoneyException();
        }
        this.money = Integer.parseInt(input);
    }


    public double calculatePercentage(double sum) {
        return sum/money;
    }

    public int getLottoCount() {
        return this.money/1000;
    }
}
