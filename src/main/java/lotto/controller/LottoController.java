package lotto.controller;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public Lottos buyLottos(int round) {
        List<Lotto> myLottos = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            NumberGenerator numberGenerator = NumberGenerator.create();
            myLottos.add(Lotto.create(numberGenerator.getNumbers()));
        }

        return Lottos.create(myLottos);
    }
}
