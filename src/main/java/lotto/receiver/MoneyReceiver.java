package lotto.receiver;

import lotto.receiver.validator.MoneyValidator;

public class MoneyReceiver {

    public static int receive(String input) {
        MoneyValidator.validate(input);
        return Integer.parseInt(input);
    }
}
