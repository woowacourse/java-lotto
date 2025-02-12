package domain;

public class LottosFactory {

    private LottoFactory lottoFactory;

    public Lottos from(final Amount amount) {
        lottoFactory = new LottoFactory();
        Lottos lottos = new Lottos();
        for (int i = 0; amount.compareAmount(i); i++) {
            Lotto lotto = lottoFactory.from();
            lottos.add(lotto);
        }
        return lottos;
    }

}
