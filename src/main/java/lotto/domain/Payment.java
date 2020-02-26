package lotto.domain;

import lotto.domain.validator.Validator;

public class Payment {
    private static final int MONEY_PER_LOTTO = 1000;
    private int payment;

    public Payment(final String inputMoney) {
        int parsedInputMoney;
        Validator.validateNumber(inputMoney);
        parsedInputMoney = Integer.parseInt(inputMoney);
        Validator.validateUnderLottoPrice(parsedInputMoney);
        Validator.validatePricePerLotto(parsedInputMoney);
        this.payment = parsedInputMoney;
    }

    public int getPurchasedCount() {
        return payment / MONEY_PER_LOTTO;
    }

}
