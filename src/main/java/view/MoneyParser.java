package view;

import java.math.BigInteger;

public class MoneyParser extends Parser<BigInteger> {
    private static final String MONEY_REGEX = "^[1-9][0-9]*0{3}$";
    static final String INVALID_MONEY_FORMAT_MESSAGE = "비정상적인 금액 형식입니다.";

    public MoneyParser() {
        super(MONEY_REGEX, INVALID_MONEY_FORMAT_MESSAGE);
    }

    @Override
    protected BigInteger convert(String text) {
        return new BigInteger(text);
    }
}
