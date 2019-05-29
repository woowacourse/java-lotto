package lotto.domain.game;

import java.util.List;

import lotto.domain.util.RandomNumbersGenerator;
import lotto.domain.lotto.Number;

public class AutoLottoGames extends LottoGames {
    private static final int FIRST_LOTTO = 0;

    AutoLottoGames(Count autoCount) {
        super();
        for (int i = FIRST_LOTTO; i < autoCount.getCount(); i++) {
            List<Number> randomNumbers = RandomNumbersGenerator.create();
            addLotto(randomNumbers);
        }
    }
}
