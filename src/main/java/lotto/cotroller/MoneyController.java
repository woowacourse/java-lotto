package lotto.cotroller;

import lotto.domain.Money;
import lotto.view.InputView;

public class MoneyController {
    public static Money request() {
        try {
            return new Money(InputView.userPrice());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return request();
        }
    }
}
