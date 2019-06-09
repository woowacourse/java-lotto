package lotto.domain;

public class MoneyParser {

    public static Money parseMoney(String money) {
        return new Money(Integer.parseInt(money));
    }
}
