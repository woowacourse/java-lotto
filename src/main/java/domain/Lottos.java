package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public Lottos(final LottoCount lottoCount) {
        LottosGenerator lottosGenerator = new ManualLottosGenerator();
        lottos.addAll(lottosGenerator.generateLottos(lottoCount));

        lottosGenerator = new AutoLottosGenerator();
        lottos.addAll(lottosGenerator.generateLottos(lottoCount));
    }

    public void addLotto(final Lotto lotto) {
        lottos.add(lotto);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}