package lotto.domain.generate;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Price;

import java.util.List;

public class LottosFactory {

    public static Lottos generateLottos(Price price,List<String> selfInputs) {
        List<Lotto> lottos = AutoLottoFactory.generateAutoLottos(price.getCountOfLotto() - selfInputs.size());
        lottos.addAll(SelfLottoFactory.generateSelfLottos((selfInputs)));
        return new Lottos(lottos);
    }

}
