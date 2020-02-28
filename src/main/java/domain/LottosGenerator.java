package domain;

import java.util.ArrayList;
import java.util.List;

public interface LottosGenerator {
    int START_INDEX = 0;

    static Lottos generateTotal(LottoCount lottoCount) {
        LottosGenerator lottosGenerator = new ManualLottosGenerator();
        List<Lotto> lottos = new ArrayList<>(lottosGenerator.generateLottos(lottoCount));

        lottosGenerator = new AutoLottosGenerator();
        lottos.addAll(lottosGenerator.generateLottos(lottoCount));
        return new Lottos(lottos);
    }

    List<Lotto> generateLottos(LottoCount lottoCount);
}
