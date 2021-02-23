package lotto.domain.lotto;


public class LottoMoney {

    public static final int LOTTO_LINE_PRICE = 1000;
    public static final String LOTTO_PURCHASE_MONEY_LACK_ERROR = "[Error] 로또 구입 비용이 부족합니다. (로또 한 라인당 1,000원)";
    private final int value;

    public LottoMoney(int value) {
        if (value < LOTTO_LINE_PRICE) {
            throw new IllegalArgumentException(LOTTO_PURCHASE_MONEY_LACK_ERROR);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public LottoMoney spendLottoLine(int lottoLineCount) {
        return new LottoMoney( value - lottoLineCount * LOTTO_LINE_PRICE);
    }

}
