package lotto;

public class LottoCount {

    private static final int LOTTO_PRICE = 1000;

    public int getLottoCount(int i) {
        return i / LOTTO_PRICE;
    }
}
