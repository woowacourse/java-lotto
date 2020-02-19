package lotto.domain;

public class Payment {
    private static final int MONEY_PER_LOTTO = 1000;
    private static final String PRICE_PER_LOTTO_ERROR_MSG = "가격은 1000원 단위로 입력해야 합니다.";
    private static final String UNDER_LOTTO_PRICE_MSG = "1000원 이상으로 입력해야 합니다.";
    private static final String NOT_NUMBER_MSG = "정수로 입력하셔야 합니다.";

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
            throw new IllegalArgumentException(NOT_NUMBER_MSG);
        }
    }

    private void validatePricePerLotto(int inputMoney) {
        if (inputMoney % MONEY_PER_LOTTO != 0) {
            throw new IllegalArgumentException(PRICE_PER_LOTTO_ERROR_MSG);
        }
    }

    private void validateUnderLottoPrice(int inputMoney) {
        if (inputMoney < MONEY_PER_LOTTO) {
            throw new IllegalArgumentException(UNDER_LOTTO_PRICE_MSG);
        }
    }

    public int getPayment() {
        return payment;
    }
}
