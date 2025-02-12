package domain;

public class LottosFactory {

    private LottoFactory lottoFactory;

    public Lottos from(final int amount) {
        lottoFactory = new LottoFactory();
        Lottos lottos = new Lottos();
        for (int i = 0; i < amount; i++) {
            Lotto lotto = lottoFactory.from();
            lottos.add(lotto);
        }
        return lottos;
    }

}
