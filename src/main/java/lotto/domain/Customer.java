package lotto.domain;

public class Customer {
    private static final String ERROR_MESSAGE_OVER_COUNT = "구입 가능한 수보다 큰 수를 입력하였습니다.";
    private static final int LOTTO_PRICE = 1000;
    private static final int TO_PERCENT_VALUE = 100;
    private static final String ERROR_MESSAGE_MIN_MONEY = "천원 이상의 금액만 가능합니다.";
    public static final int MIN_MANUAL_LOTTO_COUNT = 0;
    public static final String ERROR_MESSAGE_NULL_POINT = "입력값이 비어있습니다.";

    private final int money;
    private final int manualLottoCount;
    private String manualLottoNumber;

    public Customer(int inputMoney, int manualLottoCount) {
        validateMoneyOverThousand(inputMoney);
        validateUserLottoCount(inputMoney, manualLottoCount);
        this.money = inputMoney;
        this.manualLottoCount = manualLottoCount;
    }

    private static void validateUserLottoCount(int inputMoney, int userLottoCount) {
        if (userLottoCount > inputMoney / LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_OVER_COUNT);
        }
    }

    private void validateMoneyOverThousand(int inputMoney) {
        if (inputMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MIN_MONEY);
        }
    }

    public int divideThousand() {
        return money / LOTTO_PRICE;
    }

    public int calculateIncomeRate(long income) {
        return (int) (income * TO_PERCENT_VALUE / money);
    }

    public boolean isUserLottoCountOverZero() {
        return manualLottoCount > MIN_MANUAL_LOTTO_COUNT;
    }

    public int calculatorAutoLottoCount() {
        return divideThousand() - manualLottoCount;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public String getManualLottoNumber() {
        return manualLottoNumber;
    }

    public void setManualLottoNumber(String manualLottoNumber) {
        validateManualLottoNumbers(manualLottoNumber);
        this.manualLottoNumber = manualLottoNumber;
    }

    private void validateManualLottoNumbers(String manualLottoNumbers) {
        if (manualLottoNumbers == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_NULL_POINT);
        }
    }
}
