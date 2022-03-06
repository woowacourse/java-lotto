package lotto.domain;

import lotto.exception.LottoCounterException;

public class LottoCounter {

    private static final int LOTTO_TICKET_PRICE = 1000;

    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoCounter(LottoPurchaseMoney lottoPurchaseMoney, int manualLottoCount) {
        checkNegative(manualLottoCount);
        checkLottoCountOverMoney(lottoPurchaseMoney, manualLottoCount);
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = getTotalLottoCount(lottoPurchaseMoney) - manualLottoCount;
    }

    private void checkNegative(int manualLottoCount) {
        if (isNegativeNumber(manualLottoCount)) {
            throw new LottoCounterException(LottoCounterException.LOTTO_COUNT_NOT_NEGATIVE_NUMBER_ERROR);
        }
    }

    private boolean isNegativeNumber(int manualLottoCount) {
        return manualLottoCount < 0;
    }

    private void checkLottoCountOverMoney(LottoPurchaseMoney money, int lottoCount) {
        if (isLottoCountOverMoney(money, lottoCount)) {
            throw new LottoCounterException(LottoCounterException.LOTTO_COUNT_OVER_MONEY_ERROR);
        }
    }

    private boolean isLottoCountOverMoney(LottoPurchaseMoney lottoPurchaseMoney, int lottoCount) {
        return getTotalLottoCount(lottoPurchaseMoney) < lottoCount;
    }

    public static int getTotalLottoCount(LottoPurchaseMoney lottoPurchaseMoney) {
        return lottoPurchaseMoney.calculateTotalLottoCount(LOTTO_TICKET_PRICE);
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
