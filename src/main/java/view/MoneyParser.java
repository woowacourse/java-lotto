package view;

import java.math.BigInteger;

public class MoneyParser {
    private static final String MONEY_REGEX = "^[1-9][0-9]*0{3}$";
    static final String INVALID_MONEY_FORMAT_MESSAGE = "비정상적인 금액 형식입니다.";

    public BigInteger parse(String money) {
        if (!money.matches(MONEY_REGEX)) {
            throw new IllegalArgumentException(INVALID_MONEY_FORMAT_MESSAGE);
        }
        return new BigInteger(money);
    }
}
