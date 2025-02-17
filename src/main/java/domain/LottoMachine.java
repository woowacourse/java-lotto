package domain;

import static domain.LottoInformation.*;

public class LottoMachine {


    public LottoMachine() {
    }

    public Lottos generateLottos(int ticket) {
        Lottos lottos = new Lottos();
        for (int i = 0; i < ticket; i++) {
            Numbers randomNumbers = new Numbers(RandomGenerator.generateUniqueRandomNumbers(LOTTO_COUNT, LOTTO_NUMBER_START, LOTTO_NUMBER_END));
            Lotto lotto = new Lotto(randomNumbers);
            lottos.addLotto(lotto);
        }
        return lottos;
    }
}
