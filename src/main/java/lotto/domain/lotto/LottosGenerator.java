package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {
    public static List<Lotto> generateLottos(List<Lotto> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (Lotto lotto : numbers) {
            lottos.add(LottoMaker.generator(lotto.getLottoNumbers()));
        }
        return lottos;
    }

    public static List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoMaker.generator());
        }
        return lottos;
    }
}
