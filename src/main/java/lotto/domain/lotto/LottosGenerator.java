package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {
    public static List<Lotto> generateLottos(List<Numbers> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (Numbers lottoNumber : numbers) {
            lottos.add(LottoMaker.generator(lottoNumber));
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
