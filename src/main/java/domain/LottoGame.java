package domain;

public class LottoGame {

    public static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;

    public LottoGame(int money) {
        int lottoCount = money / LOTTO_PRICE;
        this.lottos = new Lottos(lottoCount, new RandomLottoNumberGenerator());
    }
}
