package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import lotto.exception.InvalidPriceException;
import lotto.exception.NotNumberException;

public class Payment {
    private static final int MONEY_PER_LOTTO = 1000;

    private int payment;

    public Payment(String inputMoney) {
        validateNumber(inputMoney);
        validateUnderLottoPrice(Integer.parseInt(inputMoney));
        validatePricePerLotto(Integer.parseInt(inputMoney));
        this.payment = Integer.parseInt(inputMoney);
    }

    private void validateNumber(String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new NotNumberException(ErrorMessage.NOT_NUMBER.getMessage());
        }
    }

    private void validatePricePerLotto(int inputMoney) {
        if (inputMoney % MONEY_PER_LOTTO != 0) {
            throw new InvalidPriceException(ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT.getMessage());
        }
    }

    private void validateUnderLottoPrice(int inputMoney) {
        if (inputMoney < MONEY_PER_LOTTO) {
            throw new InvalidPriceException(ErrorMessage.CAN_NOT_DIVIDE_BY_PRICE_UNIT.getMessage());
        }
    }

    public int getLottoCount() {
        return payment / MONEY_PER_LOTTO;
    }
}
