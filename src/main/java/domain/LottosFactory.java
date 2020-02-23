package domain;

import view.InputView;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {

    public static Lottos createAutoLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int index = 0; index < lottoCount; index++) {
            lottos.add(LottoFactory.createOneLotto());
        }
        return new Lottos(lottos);
    }
}
