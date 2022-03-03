package domain;

public class Money {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_AMOUNT_MINIMUM = 1;

    private static final String LOTTO_AMOUNT_ERROR = "[ERROR]: 금액이 부족해 로또를 구입할 수 없습니다. (로또는 안사는게 이득임.)";
    private static final String NOT_AFFORDABLE_MANUAL_AMOUNT_ERROR = "[ERROR] 수동 로또를 구매하기에 입력하신 금액이 너무 적습니다.";

    private final int money;
    private final int manualAmount;
    private final int autoAmount;
    private int lottoAmount;

    public Money(int money, int manualAmount) {
        this.money = money;
        convertToLottoAmount();
        checkAffordable(manualAmount);
        this.manualAmount = manualAmount;
        this.autoAmount = calculateAutoAmount();
    }

    public int getManualAmount() {
        return manualAmount;
    }

    public int getAutoAmount() {
        return autoAmount;
    }

    private void checkAffordable(int manualAmount) {
        if (lottoAmount < manualAmount) {
            throw new IllegalArgumentException(NOT_AFFORDABLE_MANUAL_AMOUNT_ERROR);
        }
    }

    private void convertToLottoAmount() {
        lottoAmount = money / LOTTO_PRICE;
        checkLottoAmount();
    }

    private int calculateAutoAmount() {
        return lottoAmount - manualAmount;
    }

    private void checkLottoAmount() {
        if (lottoAmount < LOTTO_AMOUNT_MINIMUM) {
            throw new IllegalArgumentException(LOTTO_AMOUNT_ERROR);
        }
    }

}
