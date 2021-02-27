package lotto.domain.lotto.util;


public class LottoMoney {

    public static final String LOTTO_PURCHASE_MONEY_LACK_ERROR = "[Error] 로또 구입 비용이 부족합니다. (로또 한 라인당 1,000원)";
    public static final int LOTTO_LINE_PRICE = 1000;
    private final int value;

    public LottoMoney(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_LACK_ERROR);
        }
        this.value = value;
    }

    public LottoMoney spendLottoLine(PurchaseCount lottoPurchaseCount) {
        return new LottoMoney(value - lottoPurchaseCount.getValue() * LOTTO_LINE_PRICE);
    }

    public int getCanBuyLottoLineCount() {
        return value / LOTTO_LINE_PRICE;
    }

}
