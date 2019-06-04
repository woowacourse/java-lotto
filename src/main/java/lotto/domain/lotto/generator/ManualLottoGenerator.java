package lotto.domain.lotto.generator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.generator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {
    private List<List<Integer>> manualLottos;

    public ManualLottoGenerator(List<List<Integer>> manualLottos) {
        this.manualLottos = manualLottos;
    }

    @Override
    public Lottos generate() {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> manualLotto : manualLottos) {
            lottos.add(new Lotto(manualLotto));
        }
        return new Lottos(lottos);
    }
}
