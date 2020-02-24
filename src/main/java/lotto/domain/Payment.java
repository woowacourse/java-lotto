package lotto.domain;

import lotto.domain.errors.ErrorMessage;

public class Payment {
    private static final int MONEY_PER_LOTTO = 1000;

    private int payment;

    public Payment(final String inputMoney) {
        int parsedInputMoney;
        validateNumber(inputMoney);
        parsedInputMoney = Integer.parseInt(inputMoney);
        validateUnderLottoPrice(parsedInputMoney);
        validatePricePerLotto(parsedInputMoney);
        this.payment = parsedInputMoney;
    }

    private void validateNumber(final String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            ErrorMessage nowErrorMessage = ErrorMessage.NOT_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    private void validatePricePerLotto(final int inputMoney) {
        if (inputMoney % MONEY_PER_LOTTO != 0) {
            ErrorMessage nowErrorMessage = ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    private void validateUnderLottoPrice(final int inputMoney) {
        if (inputMoney < MONEY_PER_LOTTO) {
            ErrorMessage nowErrorMessage = ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public int getLottoCount() {
        return payment / MONEY_PER_LOTTO;
    }
}
