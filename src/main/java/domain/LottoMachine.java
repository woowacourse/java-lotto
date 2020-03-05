package domain;

public class LottoMachine {
    private static final int START_INDEX = 0;

    private LottoMachine() {
    }

    public static void buyAutoLottos(Lottos lottos, LottoCount lottoCount) {
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        for (int index = START_INDEX; index < lottoCount.getAutoCount(); index++) {
            lottos.addLotto(lottoGenerator.generateLotto());
        }
    }
}
