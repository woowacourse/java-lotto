package model;

public class LottoShop {

    public static final int LOTTO_PRICE = 1000;

    public int calculateLottoAmount(int price) {
        return price / LOTTO_PRICE;
    }

    public Lottos buyLottos(int price) {
        int lottoAmount = calculateLottoAmount(price);
        return new Lottos(lottoAmount);
    }
}
