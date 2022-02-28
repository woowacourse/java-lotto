package domain;

import util.StringUtil;

public class Money {

    private static final String NONE_NUMERIC_ERROR = "[ERROR] 금액은 숫자만 입력이 가능합니다.";

    private int money;

    public Money(String money) {
        this.money = StringUtil.convertToInteger(money);
    }

    public Money(int money) {
        this.money = money;
    }

    public int money() {
        return this.money;
    }
}
