package lotto.domain.game;

import lotto.domain.util.RandomNumbersGenerator;

public class AutoLottoGames extends LottoGames {
    private static final int FIRST_LOTTO = 0;

    AutoLottoGames(Count autoCount) {
        super();
        addLottoGames(autoCount);
    }

    private void addLottoGames(Count autoCount) {
        for (int i = FIRST_LOTTO; i < autoCount.getCount(); i++) {
            addLotto(RandomNumbersGenerator.create());
        }
    }
}
