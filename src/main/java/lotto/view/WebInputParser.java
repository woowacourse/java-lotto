package lotto.view;

import lotto.domain.*;

public class WebInputParser {
    public static Money getMoney(String moneyInput) {
        return new Money(Integer.parseInt(moneyInput));
    }
}
