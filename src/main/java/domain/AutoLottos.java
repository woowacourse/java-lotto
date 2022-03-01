package domain;

import java.util.ArrayList;
import java.util.List;

public class AutoLottos extends Lottos {

    public AutoLottos(List<Lotto> lottos) {
        super(lottos);
    }

    public static AutoLottos generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.generateLottoNumbers(new RandomNumbersGenerator()));
        }
        return new AutoLottos(lottos);
    }
}
