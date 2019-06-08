package lotto.domain;

public class MoneyFactory {
    public static Money createMoney(int money){
        return new Money(money);
    }
}
