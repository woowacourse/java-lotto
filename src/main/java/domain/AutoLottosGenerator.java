package domain;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosGenerator implements LottosGenerator {

    private static LottoGenerator lottoGenerator;

    public AutoLottosGenerator() {
        lottoGenerator = new AutoLottoGenerator();
    }

    @Override
    public List<Lotto> generateLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = START_INDEX; index < lottoCount.getAutoCount(); index++) {
            lottos.add(lottoGenerator.generateLotto());
        }
        return lottos;
    }
}
