package lotto.receiver;

import lotto.receiver.validator.MoneyValidator;

public class MoneyReceiver {

    public static int receive(String input) {
        int money = Integer.parseInt(input);
        MoneyValidator.validate(money);
        return money;
    }
}
