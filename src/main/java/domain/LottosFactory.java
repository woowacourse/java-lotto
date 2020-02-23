package domain;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    public static Lottos createLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = 0; index < lottoCount; index++) {
            lottos.add(LottoFactory.createOneLotto());
        }
        return new Lottos(lottos);
    }

}
